package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.AdministrativeDTO;
import com.edanrh.apiong.repository.entities.Administrative;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AdministrativeDTOConvert {
    
    private ModelMapper mapper;

    public AdministrativeDTO toDTO(Administrative administrative) {
        return mapper.map(administrative, AdministrativeDTO.class);
    }

    public Administrative toEntity(AdministrativeDTO administrativeDTO) {
        return mapper.map(administrativeDTO, Administrative.class);
    }
}