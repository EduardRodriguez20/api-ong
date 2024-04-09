package com.edanrh.apiong.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.edanrh.apiong.dto.SanitaryDTO;
import com.edanrh.apiong.dto.converts.SanitaryDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.HeadquarterRepository;
import com.edanrh.apiong.repository.ProfessionRepository;
import com.edanrh.apiong.repository.SanitaryRepository;
import com.edanrh.apiong.repository.entities.Headquarter;
import com.edanrh.apiong.repository.entities.Profession;
import com.edanrh.apiong.repository.entities.Sanitary;
import com.edanrh.apiong.service.SanitaryService;

public class SanitaryServiceImpl implements SanitaryService{

    private SanitaryRepository sanitaryRepository;
    private ProfessionRepository professionRepository;
    private HeadquarterRepository headquarterRepository;
    private SanitaryDTOConvert dtoConvert;

    @Override
    public List<SanitaryDTO> findAll() throws ContentNullException {
        List<Sanitary> result = (List<Sanitary>) sanitaryRepository.findAll();
        if (result.isEmpty()) {
            throw new ContentNullException("code", "There isn't sanitary data", HttpStatus.NO_CONTENT);
        }else{
            List<SanitaryDTO> resultDTO = new ArrayList<>();
            for (Sanitary sanitary : result) {
                resultDTO.add(dtoConvert.toDTO(sanitary));
            }
            return resultDTO;
        }
    }

    @Override
    public SanitaryDTO findByDocument(Long document) throws NotFoundException {
        Optional<Sanitary> result = sanitaryRepository.findByDocument(document);
        if (result.isEmpty()) {
            throw new NotFoundException("document", "Document not found", HttpStatus.NOT_FOUND);
        }else {
            return dtoConvert.toDTO(result.get());
        }
    }

    @Override
    public SanitaryDTO save(SanitaryDTO sanitaryDTO) throws DuplicateCreationException, NotFoundException {
        Optional<Sanitary> existing = sanitaryRepository.findByDocument(sanitaryDTO.getData().getDocumentNumber());
        Optional<Headquarter> head = headquarterRepository.findByCodeHq(sanitaryDTO.getCodeHq());
        Optional<Profession> profession = professionRepository.findByCodePr(sanitaryDTO.getCodePr());
        if (existing.isPresent()){
            throw new DuplicateCreationException("code", "Sanitary already exists", HttpStatus.CONFLICT);
        } else if (head.isEmpty()) {
            throw new NotFoundException("code", "CodeHq invalid, headquarter don't exists", HttpStatus.NOT_FOUND);
        } else if (profession.isEmpty()) {
            throw new NotFoundException("code", "CodePr invalid, don't exist", HttpStatus.NOT_FOUND);
        } else {
            Sanitary sanitary = dtoConvert.toEntity(sanitaryDTO);
            sanitary.setHeadquarter(head.get());
            sanitary.setProfession(profession.get());
            sanitary.setIsAvailable(true);
            return dtoConvert.toDTO(sanitaryRepository.save(sanitary));
        }
    }

    @Override
    public boolean edit(Long document, SanitaryDTO sanitaryDTO) throws NotFoundException {
        Optional<Sanitary> existing = sanitaryRepository.findByDocument(document);
        Optional<Headquarter> head = headquarterRepository.findByCodeHq(sanitaryDTO.getCodeHq());
        Optional<Profession> profession = professionRepository.findByCodePr(sanitaryDTO.getCodePr());
        if (existing.isEmpty()){
            throw new NotFoundException("document", "Document not found", HttpStatus.NOT_FOUND);
        } else if (head.isEmpty()) {
            throw new NotFoundException("code", "CodeHq invalid, headquarter don't exists", HttpStatus.NOT_FOUND);
        } else if (profession.isEmpty()) {
            throw new NotFoundException("code", "CodePr invalid, don't exist", HttpStatus.NOT_FOUND);
        } else {
            Sanitary sanitary = dtoConvert.toEntity(sanitaryDTO);
            sanitary.setHeadquarter(head.get());
            sanitary.setProfession(profession.get());
            sanitaryRepository.save(sanitary);
            return true;
        }
    }

    @Override
    public boolean delete(Long document) throws NotFoundException {
        Optional<Sanitary> result = sanitaryRepository.findByDocument(document);
        if (result.isEmpty()) {
            throw new NotFoundException("document", "Document not found", HttpStatus.NOT_FOUND);
        }else {
            sanitaryRepository.delete(result.get());
            return true;
        }
    }
}
