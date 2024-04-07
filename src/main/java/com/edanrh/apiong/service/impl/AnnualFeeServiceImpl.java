package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.dto.AnnualFeeDTO;
import com.edanrh.apiong.dto.converts.AnnualFeeDTOConvert;
import com.edanrh.apiong.repository.AnnualFeeRepository;
import com.edanrh.apiong.repository.entities.AnnualFee;
import com.edanrh.apiong.service.AnnualFeeService;
import lombok.AllArgsConstructor;
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
    public List<AnnualFeeDTO> findAll() {
        List<AnnualFee> result = (List<AnnualFee>) annualFeeRepository.findAll();
        List<AnnualFeeDTO> dtoList = new ArrayList<>();
        for (AnnualFee annualFee : result) {
            dtoList.add(dtoConvert.toDTO(annualFee));
        }
        return dtoList;
    }

    @Override
    public AnnualFeeDTO save(AnnualFeeDTO annualFeeDTO) {
        AnnualFee fee = annualFeeRepository.save(dtoConvert.toEntity(annualFeeDTO));
        return dtoConvert.toDTO(fee);
    }

    @Override
    public boolean edit(String name, AnnualFeeDTO annualFeeDTO) {
        Optional<AnnualFee> entity = annualFeeRepository.findByName(name);
        if (entity.isPresent()) {
            entity.get().setName(annualFeeDTO.getName());
            entity.get().setAmount(annualFeeDTO.getAmount());
            annualFeeRepository.save(entity.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String name) {
        Optional<AnnualFee> entity = annualFeeRepository.findByName(name);
        if (entity.isPresent()) {
            annualFeeRepository.delete(entity.get());
            return true;
        }
        return false;
    }
}
