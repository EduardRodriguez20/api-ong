package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.dto.PartnerDTO;
import com.edanrh.apiong.dto.converts.PartnerDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.AnnualFeeRepository;
import com.edanrh.apiong.repository.HeadquarterRepository;
import com.edanrh.apiong.repository.PartnerRepository;
import com.edanrh.apiong.repository.entities.AnnualFee;
import com.edanrh.apiong.repository.entities.Headquarter;
import com.edanrh.apiong.repository.entities.Partner;
import com.edanrh.apiong.service.PartnerService;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PartnerServiceImpl implements PartnerService {

    private PartnerRepository partnerRepository;
    private AnnualFeeRepository annualFeeRepository;
    private HeadquarterRepository headquarterRepository;
    private PartnerDTOConvert dtoConvert;

    @Override
    public List<PartnerDTO> findAll() throws ContentNullException {
        List<Partner> result = (List<Partner>) partnerRepository.findAll();
        if (result.isEmpty()){
            throw new ContentNullException("code", "There isn't partner data", HttpStatus.NO_CONTENT);
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
    public PartnerDTO save(PartnerDTO partner) throws DuplicateCreationException, NotFoundException {
        Optional<Headquarter> head = headquarterRepository.findByCodeHq(partner.getCodeHq());
        Optional<Partner> existing = partnerRepository.findByDocument(partner.getData().getDocumentNumber());
        Optional<AnnualFee> annualFee = annualFeeRepository.findByName(partner.getFee());
        if (existing.isPresent()){
            throw new DuplicateCreationException("code", "Partner already exists", HttpStatus.CONFLICT);
        } else if (head.isEmpty()) {
            throw new NotFoundException("code", "CodeHq invalid, headquarter don't exists", HttpStatus.NOT_FOUND);
        } else if (annualFee.isEmpty()) {
            throw new NotFoundException("code", "CodeFee invalid, annual fee don't exist", HttpStatus.NOT_FOUND);
        } else {
            Partner entity = dtoConvert.toEntity(partner);
            entity.setHeadquarter(head.get());
            entity.setFee(annualFee.get());
            entity.generatePaymentDate();
            return dtoConvert.toDTO(partnerRepository.save(entity));
        }
    }

    @Override
    public boolean edit(Long document, PartnerDTO partner) throws NotFoundException {
        Optional<Headquarter> head = headquarterRepository.findByCodeHq(partner.getCodeHq());
        Optional<Partner> existing = partnerRepository.findByDocument(partner.getData().getDocumentNumber());
        Optional<AnnualFee> annualFee = annualFeeRepository.findByName(partner.getFee());
        if (existing.isEmpty()){
            throw new NotFoundException("code", "Document invalid, don't partner exist", HttpStatus.NOT_FOUND);
        } else if (head.isEmpty()) {
            throw new NotFoundException("code", "CodeHq invalid, headquarter don't exists", HttpStatus.NOT_FOUND);
        } else if (annualFee.isEmpty()) {
            throw new NotFoundException("code", "CodeFee invalid, annual fee don't exist", HttpStatus.NOT_FOUND);
        }else {
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
