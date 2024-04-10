package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.common.ErrorCode;
import com.edanrh.apiong.dto.ShelterDTO;
import com.edanrh.apiong.dto.converts.ShelterDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.ReferencedEntityException;
import com.edanrh.apiong.repository.CityRepository;
import com.edanrh.apiong.repository.ShelterRepository;
import com.edanrh.apiong.repository.ShipmentRepository;
import com.edanrh.apiong.repository.entities.City;
import com.edanrh.apiong.repository.entities.Shelter;
import com.edanrh.apiong.repository.entities.Shipment;
import com.edanrh.apiong.service.ShelterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private ShelterRepository shelterRepository;
    private CityRepository cityRepository;
    private ShipmentRepository shipmentRepository;
    private ShelterDTOConvert dtoConvert;

    @Override
    public List<ShelterDTO> findAll() throws ContentNullException {
        List<Shelter> shelters = (List<Shelter>) shelterRepository.findAll();
        if (shelters.isEmpty()){
            throw new ContentNullException(ErrorCode.SHELTER_CONTENT_NOT_FOUND, "There aren't shelter data", HttpStatus.NO_CONTENT);
        }else {
            List<ShelterDTO> resultDTO = new ArrayList<>();
            for (Shelter shelter : shelters) {
                resultDTO.add(dtoConvert.toDTO(shelter));
            }
            return resultDTO;
        }
    }

    @Override
    public ShelterDTO findByCodeSh(String codeSh) throws NotFoundException {
        Optional<Shelter> shelter = shelterRepository.findByCodeSh(codeSh);
        if (shelter.isEmpty()){
            throw new NotFoundException(ErrorCode.SHELTER_CODE_NOT_FOUND, "Shelter not found, codeSh invalid", HttpStatus.NOT_FOUND);
        }else {
            return dtoConvert.toDTO(shelter.get());
        }
    }

    @Override
    public ShelterDTO save(ShelterDTO shelterDTO) throws NotFoundException {
        Optional<City> city = cityRepository.findByName(shelterDTO.getCity());
        if (city.isEmpty()){
            throw new NotFoundException(ErrorCode.CITY_NAME_NOT_FOUND, "City not found, city invalid", HttpStatus.NOT_FOUND);
        }else {
            Shelter shelter = dtoConvert.toEntity(shelterDTO);
            shelter.setCity(city.get());
            Shelter saved = shelterRepository.save(shelter);
            shelter.setId(saved.getId());
            shelter.generateCodeSh();
            System.out.println(shelter);
            return dtoConvert.toDTO(shelterRepository.save(shelter));
        }
    }

    @Override
    public boolean edit(String codeSh, ShelterDTO shelterDTO) throws NotFoundException {
        Optional<Shelter> existing = shelterRepository.findByCodeSh(codeSh);
        Optional<City> city = cityRepository.findByName(shelterDTO.getCity());
        if (city.isEmpty()){
            throw new NotFoundException(ErrorCode.CITY_NAME_NOT_FOUND, "City not found, city invalid", HttpStatus.NOT_FOUND);
        } else if (existing.isEmpty()) {
            throw new NotFoundException(ErrorCode.SHELTER_CODE_NOT_FOUND, "Shelter not found, codeSh invalid", HttpStatus.NOT_FOUND);
        } else {
            Shelter shelter = dtoConvert.toEntity(shelterDTO);
            shelter.setCity(city.get());
            Shelter saved = shelterRepository.save(shelter);
            shelter.setId(saved.getId());
            shelter.generateCodeSh();
            shelterRepository.save(shelter);
            return true;
        }
    }

    @Override
    public boolean deleteByCodeSh(String codeSh) throws NotFoundException, ReferencedEntityException {
        Optional<Shelter> existing = shelterRepository.findByCodeSh(codeSh);
        if (existing.isEmpty()){
            throw new NotFoundException(ErrorCode.SHELTER_CODE_NOT_FOUND, "Shelter not found, codeSh invalid", HttpStatus.NOT_FOUND);
        }else {
            Optional<Shipment> shipment = shipmentRepository.findFirstByCodeSh(existing.get().getCodeSh());
            if (shipment.isPresent()){
                throw new ReferencedEntityException(ErrorCode.SHELTER_REFERENCE_EXISTING, "Shelter has shipments linked", HttpStatus.CONFLICT);
            } else {
                shelterRepository.delete(existing.get());
                return true;
            }
        }
    }
}
