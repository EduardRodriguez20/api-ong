package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.common.ValidateEmail;
import com.edanrh.apiong.dto.PartnerDTO;
import com.edanrh.apiong.dto.converts.PartnerDTOConvert;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.AnnualFeeRepository;
import com.edanrh.apiong.repository.HeadquarterRepository;
import com.edanrh.apiong.repository.PartnerRepository;
import com.edanrh.apiong.repository.PersonRepository;
import com.edanrh.apiong.repository.entities.*;
import com.edanrh.apiong.service.PartnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private PartnerRepository partnerRepository;
    private AnnualFeeRepository annualFeeRepository;
    private HeadquarterRepository headquarterRepository;
    private PersonRepository personRepository;
    private PartnerDTOConvert dtoConvert;

    @Override
    public List<PartnerDTO> findAll() throws ContentNullException {
        List<Partner> result = (List<Partner>) partnerRepository.findAll();
        if (result.isEmpty()){
            throw new ContentNullException("code", "There aren't partner data", HttpStatus.NO_CONTENT);
        }else {
            List<PartnerDTO> resultDTO = new ArrayList<>();
            for (Partner partner : result) {
                PartnerDTO dto = dtoConvert.toDTO(partner);
                dto.setCodeHq(partner.getHeadquarter().getCodeHq());
                dto.setFee(partner.getFee().getName());
                resultDTO.add(dto);
            }
            return resultDTO;
        }
    }

    @Override
    public PartnerDTO findByDocument(Long document) throws NotFoundException {
        Optional<Partner> result = partnerRepository.findByDocument(document);
        if (result.isEmpty()){
            throw new NotFoundException("document", "Document not found", HttpStatus.NOT_FOUND);
        }else {
            return dtoConvert.toDTO(result.get());
        }
    }

    @Override
    public PartnerDTO save(PartnerDTO partner) throws DuplicateCreationException, NotFoundException, BussinesRuleException {
        Optional<Headquarter> head = headquarterRepository.findByCodeHq(partner.getCodeHq());
        Optional<Person> existing = personRepository.findByDocumentNumber(partner.getData().getDocumentNumber());
        Optional<AnnualFee> annualFee = annualFeeRepository.findByName(partner.getFee());
        Optional<Person> email = personRepository.findByEmail(partner.getData().getEmail());
        if (existing.isPresent()){
            throw new DuplicateCreationException("code", "Document number belongs to another person", HttpStatus.CONFLICT);
        } else if (head.isEmpty()) {
            throw new NotFoundException("code", "CodeHq invalid, headquarter don't exists", HttpStatus.NOT_FOUND);
        } else if (annualFee.isEmpty()) {
            throw new NotFoundException("code", "CodeFee invalid, annual fee don't exist", HttpStatus.NOT_FOUND);
        } else if (email.isPresent()) {
            throw new DuplicateCreationException("code", "Email isn't available, already exists", HttpStatus.CONFLICT);
        } else if (!ValidateEmail.validateEmail(partner.getData().getEmail())) {
            throw new BussinesRuleException("code", "Must be a valid email address", HttpStatus.CONFLICT);
        } else {
            Partner entity = dtoConvert.toEntity(partner);
            entity.setHeadquarter(head.get());
            entity.setFee(annualFee.get());
            entity.generatePaymentDate();
            return dtoConvert.toDTO(partnerRepository.save(entity));
        }
    }

    @Override
    public boolean edit(Long document, PartnerDTO partner) throws NotFoundException, DuplicateCreationException, BussinesRuleException {
        Optional<Headquarter> head = headquarterRepository.findByCodeHq(partner.getCodeHq());
        Optional<Partner> existing = partnerRepository.findByDocument(partner.getData().getDocumentNumber());
        Optional<AnnualFee> annualFee = annualFeeRepository.findByName(partner.getFee());
        Optional<Person> email = personRepository.findByEmail(partner.getData().getEmail());
        Optional<Person> personExisting = personRepository.findByDocumentNumber(partner.getData().getDocumentNumber());
        if (existing.isEmpty()){
            throw new NotFoundException("code", "Document invalid, don't partner exist", HttpStatus.NOT_FOUND);
        } else if (head.isEmpty()) {
            throw new NotFoundException("code", "CodeHq invalid, headquarter don't exists", HttpStatus.NOT_FOUND);
        } else if (annualFee.isEmpty()) {
            throw new NotFoundException("code", "CodeFee invalid, annual fee don't exist", HttpStatus.NOT_FOUND);
        } else if (email.isPresent()) {
            throw new DuplicateCreationException("code", "Email isn't available, already exists", HttpStatus.CONFLICT);
        } else if (personExisting.isPresent()) {
            throw new DuplicateCreationException("code", "Document number belongs to another person", HttpStatus.CONFLICT);
        } else if (!ValidateEmail.validateEmail(partner.getData().getEmail())) {
            throw new BussinesRuleException("code", "Must be a valid email address", HttpStatus.CONFLICT);
        } else {
            Partner entity = dtoConvert.toEntity(partner);
            entity.setHeadquarter(head.get());
            entity.setFee(annualFee.get());
            entity.setPaymentDate(existing.get().getPaymentDate());
            partnerRepository.save(entity);
            return true;
        }
    }

    @Override
    public boolean delete(Long document) throws NotFoundException {
        Optional<Partner> existing = partnerRepository.findByDocument(document);
        if(existing.isEmpty()){
            throw new NotFoundException("document", "Document invalid, don't exist", HttpStatus.NOT_FOUND);
        }else{
            partnerRepository.delete(existing.get());
            return true;
        }
    }
}
