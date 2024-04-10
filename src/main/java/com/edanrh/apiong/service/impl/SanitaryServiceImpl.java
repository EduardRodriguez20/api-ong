package com.edanrh.apiong.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.edanrh.apiong.common.ValidateEmail;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.repository.PersonRepository;
import com.edanrh.apiong.repository.entities.Person;
import lombok.AllArgsConstructor;
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
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SanitaryServiceImpl implements SanitaryService{

    private SanitaryRepository sanitaryRepository;
    private ProfessionRepository professionRepository;
    private HeadquarterRepository headquarterRepository;
    private PersonRepository personRepository;
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
    public boolean isAvailable(Long document) throws NotFoundException {
        Optional<Sanitary> result = sanitaryRepository.findByDocument(document);
        if (result.isEmpty()) {
            throw new NotFoundException("document", "Document not found", HttpStatus.NOT_FOUND);
        } else {
            result.get().available();
            sanitaryRepository.save(result.get());
            return true;
        }
    }

    @Override
    public SanitaryDTO save(SanitaryDTO sanitaryDTO) throws DuplicateCreationException, NotFoundException, BussinesRuleException {
        Optional<Sanitary> existing = sanitaryRepository.findByDocument(sanitaryDTO.getData().getDocumentNumber());
        Optional<Headquarter> head = headquarterRepository.findByCodeHq(sanitaryDTO.getCodeHq());
        Optional<Profession> profession = professionRepository.findByCodePr(sanitaryDTO.getCodePr());
        Optional<Person> person = personRepository.findByEmail(sanitaryDTO.getData().getEmail());
        if (existing.isPresent()){
            throw new DuplicateCreationException("code", "Sanitary already exists", HttpStatus.CONFLICT);
        } else if (head.isEmpty()) {
            throw new NotFoundException("code", "CodeHq invalid, headquarter don't exists", HttpStatus.NOT_FOUND);
        } else if (profession.isEmpty()) {
            throw new NotFoundException("code", "CodePr invalid, don't exist", HttpStatus.NOT_FOUND);
        } else if (person.isPresent()) {
            throw new DuplicateCreationException("code", "Email isn't available, already exists", HttpStatus.CONFLICT);
        } else if (!ValidateEmail.validateEmail(sanitaryDTO.getData().getEmail())) {
            throw new BussinesRuleException("code", "Must be a valid email address", HttpStatus.CONFLICT);
        } else {
            Sanitary sanitary = dtoConvert.toEntity(sanitaryDTO);
            sanitary.setHeadquarter(head.get());
            sanitary.setProfession(profession.get());
            sanitary.setIsAvailable(true);
            sanitary.setParticipation(0);
            return dtoConvert.toDTO(sanitaryRepository.save(sanitary));
        }
    }

    @Override
    public boolean edit(Long document, SanitaryDTO sanitaryDTO) throws NotFoundException, DuplicateCreationException, BussinesRuleException {
        Optional<Sanitary> existing = sanitaryRepository.findByDocument(document);
        Optional<Headquarter> head = headquarterRepository.findByCodeHq(sanitaryDTO.getCodeHq());
        Optional<Profession> profession = professionRepository.findByCodePr(sanitaryDTO.getCodePr());
        Optional<Person> person = personRepository.findByEmail(sanitaryDTO.getData().getEmail());
        if (existing.isEmpty()){
            throw new NotFoundException("document", "Document not found", HttpStatus.NOT_FOUND);
        } else if (head.isEmpty()) {
            throw new NotFoundException("code", "CodeHq invalid, headquarter don't exists", HttpStatus.NOT_FOUND);
        } else if (profession.isEmpty()) {
            throw new NotFoundException("code", "CodePr invalid, don't exist", HttpStatus.NOT_FOUND);
        } else if (person.isPresent()) {
            throw new DuplicateCreationException("code", "Email isn't available, already exists", HttpStatus.CONFLICT);
        } else if (!ValidateEmail.validateEmail(sanitaryDTO.getData().getEmail())) {
            throw new BussinesRuleException("code", "Must be a valid email address", HttpStatus.CONFLICT);
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
