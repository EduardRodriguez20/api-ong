package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.AnnualFeeDTO;
import com.edanrh.apiong.repository.entities.AnnualFee;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AnnualFeeDTOConvert {
    
    private ModelMapper mapper;

    public AnnualFeeDTO toDTO(AnnualFee annualFee) {
        return mapper.map(annualFee, AnnualFeeDTO.class);
    }

    public AnnualFee toEntity(AnnualFeeDTO annualFeeDTO) {
        return mapper.map(annualFeeDTO, AnnualFee.class);
    }
}
