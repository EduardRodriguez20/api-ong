package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.dto.AnnualFeeDTO;
import com.edanrh.apiong.dto.converts.AnnualFeeDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.AnnualFeeRepository;
import com.edanrh.apiong.repository.entities.AnnualFee;
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
    private AnnualFeeDTOConvert dtoConvert;

    @Override
    public List<AnnualFeeDTO> findAll() throws ContentNullException {
        List<AnnualFee> result = (List<AnnualFee>) annualFeeRepository.findAll();
        if (result.isEmpty()){
            throw new ContentNullException("code", "There isn't Annual fees data", HttpStatus.NO_CONTENT);
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
        if (entity.isPresent()) {
            throw new DuplicateCreationException("code", "Annual Fee already exists", HttpStatus.CONFLICT);
        }else {
            AnnualFee fee = annualFeeRepository.save(dtoConvert.toEntity(annualFeeDTO));
            return dtoConvert.toDTO(fee);
        }
    }

    @Override
    public boolean edit(String name, AnnualFeeDTO annualFeeDTO) throws NotFoundException {
        Optional<AnnualFee> entity = annualFeeRepository.findByName(name);
        if (entity.isPresent()) {
            entity.get().setName(annualFeeDTO.getName());
            entity.get().setAmount(annualFeeDTO.getAmount());
            annualFeeRepository.save(entity.get());
            return true;
        }else {
            throw new NotFoundException("code", "Annual Fee doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean delete(String name) throws NotFoundException {
        Optional<AnnualFee> entity = annualFeeRepository.findByName(name);
        if (entity.isPresent()) {
            annualFeeRepository.delete(entity.get());
            return true;
        }else {
            throw new NotFoundException("code", "Annual Fee doesn't exist", HttpStatus.NOT_FOUND);
        }
    }
}
