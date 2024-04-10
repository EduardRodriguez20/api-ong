package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.HeadquarterDTO;
import com.edanrh.apiong.repository.entities.Headquarter;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HeadquarterDTOConvert {
    
    private ModelMapper mapper;

    public HeadquarterDTO toDTO(Headquarter headquarter) {
        HeadquarterDTO dto = mapper.map(headquarter, HeadquarterDTO.class);
        dto.setCity(headquarter.getCity().getName());
        return dto;
    }

    public Headquarter toEntity(HeadquarterDTO headquarterDTO) {
        return mapper.map(headquarterDTO, Headquarter.class);
    }

}
