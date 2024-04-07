package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.ProfessionDTO;
import com.edanrh.apiong.repository.entities.Profession;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProfessionDTOConvert {
    
    private ModelMapper mapper;

    public ProfessionDTO toDTO(Profession profession){
        return mapper.map(profession, ProfessionDTO.class);
    }

    public Profession toEntity(ProfessionDTO professionDTO){
        return mapper.map(professionDTO, Profession.class);
    }
}
