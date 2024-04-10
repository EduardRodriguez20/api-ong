package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.common.ErrorCode;
import com.edanrh.apiong.dto.AnnualFeeDTO;
import com.edanrh.apiong.dto.converts.AnnualFeeDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.ReferencedEntityException;
import com.edanrh.apiong.repository.AnnualFeeRepository;
import com.edanrh.apiong.repository.PartnerRepository;
import com.edanrh.apiong.repository.entities.AnnualFee;
import com.edanrh.apiong.repository.entities.Partner;
import com.edanrh.apiong.service.AnnualFeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnnualFeeServiceImpl implements AnnualFeeService {

    private AnnualFeeRepository annualFeeRepository;
    private PartnerRepository partnerRepository;
    private AnnualFeeDTOConvert dtoConvert;

    @Override
    public List<AnnualFeeDTO> findAll() throws ContentNullException {
        List<AnnualFee> result = (List<AnnualFee>) annualFeeRepository.findAll();
        if (result.isEmpty()){
            throw new ContentNullException(ErrorCode.ANNUAL_FEE_CONTENT_NOT_FOUND, "There isn't Annual fees data", HttpStatus.NO_CONTENT);
        }else {
            List<AnnualFeeDTO> dtoList = new ArrayList<>();
            for (AnnualFee annualFee : result) {
                dtoList.add(dtoConvert.toDTO(annualFee));
            }
            return dtoList;
        }
    }

    @Override
    public AnnualFeeDTO save(AnnualFeeDTO annualFeeDTO) throws DuplicateCreationException {
        Optional<AnnualFee> entity = annualFeeRepository.findByName(annualFeeDTO.getName());
        Optional<AnnualFee> existAmount = annualFeeRepository.findByAmount(annualFeeDTO.getAmount());
        if (entity.isPresent()) {
            throw new DuplicateCreationException(ErrorCode.ANNUAL_FEE_DUPLICATE_CREATION, "Annual Fee name already exists", HttpStatus.CONFLICT);
        } else if (existAmount.isPresent()) {
            throw new DuplicateCreationException(ErrorCode.ANNUAL_FEE_DUPLICATE_CREATION, "Annual Fee amount already exists", HttpStatus.CONFLICT);
        } else {
            AnnualFee fee = annualFeeRepository.save(dtoConvert.toEntity(annualFeeDTO));
            return dtoConvert.toDTO(fee);
        }
    }

    @Override
    public boolean edit(String name, AnnualFeeDTO annualFeeDTO) throws NotFoundException, DuplicateCreationException {
        Optional<AnnualFee> entity = annualFeeRepository.findByName(name);
        Optional<AnnualFee> existAmount = annualFeeRepository.findByAmount(annualFeeDTO.getAmount());
        if (entity.isPresent()) {
            if (existAmount.isPresent()) {
                throw new DuplicateCreationException(ErrorCode.ANNUAL_FEE_DUPLICATE_CREATION, "Annual Fee amount already exists", HttpStatus.CONFLICT);
            }else {
                entity.get().setName(annualFeeDTO.getName());
                entity.get().setAmount(annualFeeDTO.getAmount());
                annualFeeRepository.save(entity.get());
                return true;
            }
        }else {
            throw new NotFoundException(ErrorCode.ANNUAL_FEE_NAME_NOT_FOUND, "Annual Fee doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean delete(String name) throws NotFoundException, ReferencedEntityException {
        Optional<AnnualFee> entity = annualFeeRepository.findByName(name);
        Optional<Partner> partner = partnerRepository.findFirstByNameFee(name);
        if (entity.isEmpty()) {
            throw new NotFoundException(ErrorCode.ANNUAL_FEE_NAME_NOT_FOUND, "Annual Fee doesn't exist", HttpStatus.NOT_FOUND);
        }else if (partner.isPresent()){
            throw new ReferencedEntityException(ErrorCode.ANNUAL_FEE_REFERENCED_EXIST, "Annual fee has partners relationship", HttpStatus.CONFLICT);
        } else {
            annualFeeRepository.delete(entity.get());
            return true;
        }
    }
}
