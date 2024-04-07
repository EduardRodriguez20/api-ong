package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.SanitaryDTO;
import com.edanrh.apiong.repository.entities.Sanitary;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SanitaryDTOConvert {
    
    private ModelMapper mapper;

    public SanitaryDTO toDTO(Sanitary sanitary){
        return mapper.map(sanitary, SanitaryDTO.class);
    }

    public Sanitary toEntity(SanitaryDTO sanitaryDTO){
        return mapper.map(sanitaryDTO, Sanitary.class);
    }
}
