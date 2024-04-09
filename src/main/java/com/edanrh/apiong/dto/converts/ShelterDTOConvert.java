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
        ShelterDTO dto = modelMapper.map(shelter, ShelterDTO.class);
        dto.setCity(shelter.getCity().getName());
        return dto;
    }

    public Shelter toEntity(ShelterDTO shelterDTO) {
        return modelMapper.map(shelterDTO, Shelter.class);
    }
}
