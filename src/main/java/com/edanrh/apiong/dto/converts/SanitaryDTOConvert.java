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
    private PersonDTOConvert personDTOConvert;

    public SanitaryDTO toDTO(Sanitary sanitary){
        SanitaryDTO dto = mapper.map(sanitary, SanitaryDTO.class);
        dto.setData(personDTOConvert.toDTO(sanitary));
        dto.setCodeHq(sanitary.getHeadquarter().getCodeHq());
        dto.setCodePr(sanitary.getProfession().getCodePr());
        return dto;
    }

    public Sanitary toEntity(SanitaryDTO sanitaryDTO){
        Sanitary sanitary = mapper.map(sanitaryDTO, Sanitary.class);
        System.out.println("sanitary dto before add data"+sanitary + " "+ sanitaryDTO.getData());
        personDTOConvert.addPersonalData(sanitary, sanitaryDTO.getData());
        System.out.println("sanitary dto after add data"+sanitary);
        return sanitary;
    }
}
