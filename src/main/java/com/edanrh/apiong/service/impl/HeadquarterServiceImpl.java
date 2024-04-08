package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.dto.HeadquarterDTO;
import com.edanrh.apiong.dto.converts.HeadquarterDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.ReferencedEntityException;
import com.edanrh.apiong.repository.*;
import com.edanrh.apiong.repository.entities.*;
import com.edanrh.apiong.service.HeadquarterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HeadquarterServiceImpl implements HeadquarterService {

    private HeadquarterRepository headquarterRepository;
    private AdministrativeRepository administrativeRepository;
    private CityRepository cityRepository;
    private DirectorRepository directorRepository;
    private HumanitarianAidRepository humanitarianAidRepository;
    private MaterialAidRepository materialAidRepository;
    private PartnerRepository partnerRepository;
    private SanitaryRepository sanitaryRepository;
    private HeadquarterDTOConvert dtoConvert;

    @Override
    public List<HeadquarterDTO> findAll() throws ContentNullException {
        List<Headquarter> result = (List<Headquarter>) headquarterRepository.findAll();
        if (result.isEmpty()) {
            throw new ContentNullException("code", "There isn't headquarters data", HttpStatus.NO_CONTENT);
        }else {
            List<HeadquarterDTO> resultDTO = new ArrayList<>();
            for (Headquarter headquarter : result) {
                resultDTO.add(dtoConvert.toDTO(headquarter));
            }
            return resultDTO;
        }
    }

    @Override
    public HeadquarterDTO findByCodeHq(String codeHq) throws NotFoundException {
        Optional<Headquarter> headquarter = headquarterRepository.findByCodeHq(codeHq);
        if (headquarter.isEmpty()){
            throw new NotFoundException("code", "Headquarter not found, codeHq invalid", HttpStatus.NOT_FOUND);
        }else{
            return dtoConvert.toDTO(headquarter.get());
        }
    }

    @Override
    public HeadquarterDTO save(HeadquarterDTO headquarterDTO) throws NotFoundException, DuplicateCreationException {
        Optional<City> city = cityRepository.findByName(headquarterDTO.getCity());
        if (city.isEmpty()){
            throw new NotFoundException("code", "City not found, city invalid", HttpStatus.NOT_FOUND);
        }else {
            if (headquarterRepository.existsByCityName(headquarterDTO.getCity())){
                throw new DuplicateCreationException("code", "Headquarter already exists in that city", HttpStatus.CONFLICT);
            }else {
                Headquarter entity = dtoConvert.toEntity(headquarterDTO);
                entity.setCity(city.get());
                Headquarter saved = headquarterRepository.save(entity);
                saved.generateCodeHq();
                System.out.println(saved.getCodeHq());
                return dtoConvert.toDTO(headquarterRepository.save(entity));
            }
        }
    }

    @Override
    public boolean edit(String codeHq, HeadquarterDTO headquarterDTO) throws NotFoundException {
        Optional<Headquarter> entity = headquarterRepository.findByCodeHq(codeHq);
        Optional<City> city = cityRepository.findByName(headquarterDTO.getCity());
        if (entity.isEmpty()){
            throw new NotFoundException("code", "CodeHq invalid, don't exists", HttpStatus.NOT_FOUND);
        } else if (city.isEmpty()){
            throw new NotFoundException("code", "City invalid, don't exist", HttpStatus.NOT_FOUND);
        } else {
            Headquarter headquarter = dtoConvert.toEntity(headquarterDTO);
            headquarter.setCity(city.get());
            headquarterRepository.save(headquarter);
            return true;
        }
    }

    @Override
    public boolean deleteByCodeHq(String codeHq) throws NotFoundException, ReferencedEntityException {
        Optional<Administrative> admin = administrativeRepository.findByCodeHq(codeHq);
        Optional<Director> director = directorRepository.findByCodeHq(codeHq);
        Optional<HumanitarianAid> humanitarianAid = humanitarianAidRepository.findByCodeHq(codeHq);
        Optional<MaterialAid> materialAid = materialAidRepository.findByCodeHq(codeHq);
        Optional<Partner> partner = partnerRepository.findByCodeHq(codeHq);
        Optional<Sanitary> sanitary = sanitaryRepository.findByCodeHq(codeHq);
        Optional<Headquarter> entity = headquarterRepository.findByCodeHq(codeHq);
        if (entity.isEmpty()){
            throw new NotFoundException("code", "CodeHq invalid, don't exists", HttpStatus.NOT_FOUND);
        } else if (admin.isPresent()) {
            throw new ReferencedEntityException("code", "Headquarter has admins linked", HttpStatus.CONFLICT);
        } else if (director.isPresent()) {
            throw new ReferencedEntityException("code", "Headquarter has directors linked", HttpStatus.CONFLICT);
        } else if (humanitarianAid.isPresent()) {
            throw new ReferencedEntityException("code", "Headquarter has humanitarian aid linked", HttpStatus.CONFLICT);
        } else if (materialAid.isPresent()) {
            throw new ReferencedEntityException("code", "Headquarter has material aid linked", HttpStatus.CONFLICT);
        } else if (partner.isPresent()) {
            throw new ReferencedEntityException("code", "Headquarter has partners linked", HttpStatus.CONFLICT);
        } else if (sanitary.isPresent()) {
            throw new ReferencedEntityException("code", "Headquarter has sanitaries linked", HttpStatus.CONFLICT);
        } else {
            headquarterRepository.delete(entity.get());
            return true;
        }
    }
}
