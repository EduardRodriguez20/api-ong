package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.dto.AdministrativeDTO;
import com.edanrh.apiong.dto.converts.AdministrativeDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.AdministrativeRepository;
import com.edanrh.apiong.repository.HeadquarterRepository;
import com.edanrh.apiong.repository.ProfessionRepository;
import com.edanrh.apiong.repository.entities.Administrative;
import com.edanrh.apiong.repository.entities.Headquarter;
import com.edanrh.apiong.repository.entities.Profession;
import com.edanrh.apiong.service.AdministrativeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdministrativeServiceImpl implements AdministrativeService {

    private AdministrativeRepository administrativeRepository;
    private HeadquarterRepository headquarterRepository;
    private ProfessionRepository professionRepository;
    private AdministrativeDTOConvert dtoConvert;

    @Override
    public List<AdministrativeDTO> findAll() throws ContentNullException {
        List<Administrative> result = (List<Administrative>) administrativeRepository.findAll();
        if (result.isEmpty()){
            throw new ContentNullException("code", "There isn't administrative data", HttpStatus.NO_CONTENT);
        }
        List<AdministrativeDTO> resultDTO = new ArrayList<>();
        for (Administrative entity : result) {
            resultDTO.add(dtoConvert.toDTO(entity));
        }
        return resultDTO;
    }

    @Override
    public AdministrativeDTO findByDocument(Long document) throws NotFoundException {
        Optional<Administrative> result = administrativeRepository.findByDocument(document);
        if (result.isEmpty()) {
            throw new NotFoundException("document", "Document not found", HttpStatus.NOT_FOUND);
        }
        return dtoConvert.toDTO(result.get());
    }

    @Override
    public AdministrativeDTO save(AdministrativeDTO administrativeDTO) throws NotFoundException, DuplicateCreationException {
        Optional<Headquarter> head = headquarterRepository.findByCodeHq(administrativeDTO.getCodeHq());
        Optional<Profession> profession = professionRepository.findByCodePr(administrativeDTO.getCodePr());
        Optional<Administrative> existing = administrativeRepository.findByDocument(administrativeDTO.getData().getDocumentNumber());
        if (existing.isPresent()){
            throw new DuplicateCreationException("code", "Administrative already exists", HttpStatus.CONFLICT);
        }else if (head.isEmpty()){
            throw new NotFoundException("code", "CodeHq invalid, don't exists", HttpStatus.NOT_FOUND);
        } else if (profession.isEmpty()){
            throw new NotFoundException("code", "CodePr invalid, don't exist", HttpStatus.NOT_FOUND);
        }else {
            Administrative entity = dtoConvert.toEntity(administrativeDTO);
            entity.setHeadquarter(head.get());
            entity.setProfession(profession.get());
            return dtoConvert.toDTO(administrativeRepository.save(entity));
        }
    }

    @Override
    public boolean edit(Long document, AdministrativeDTO administrativeDTO) throws NotFoundException {
        Optional<Administrative> result = administrativeRepository.findByDocument(document);
        if (result.isPresent()) {
            Administrative save = dtoConvert.toEntity(administrativeDTO);
            save.setId(result.get().getId());
            administrativeRepository.save(save);
            return true;
        }else {
            throw new NotFoundException("code", "Document invalid, don't exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean delete(Long document) throws NotFoundException {
        Optional<Administrative> result = administrativeRepository.findByDocument(document);
        if (result.isPresent()) {
            administrativeRepository.delete(result.get());
            return true;
        }else{
            throw new NotFoundException("code", "Document invalid, don't exist", HttpStatus.NOT_FOUND);
        }
    }
}
