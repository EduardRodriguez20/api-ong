package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.ShelterDTO;
import com.edanrh.apiong.repository.entities.Shelter;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ShelterDTOConvert {
    
    private ModelMapper modelMapper;

    public ShelterDTO toDTO(Shelter shelter) {
        return modelMapper.map(shelter, ShelterDTO.class);
    }

    public Shelter toEntity(ShelterDTO shelterDTO) {
        return modelMapper.map(shelterDTO, Shelter.class);
    }
}
