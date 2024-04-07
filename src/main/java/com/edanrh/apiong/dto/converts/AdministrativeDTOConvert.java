package com.edanrh.apiong.dto.converts;

import com.edanrh.apiong.repository.entities.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.AdministrativeDTO;
import com.edanrh.apiong.repository.entities.Administrative;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AdministrativeDTOConvert {
    
    private ModelMapper mapper;
    private PersonDTOConvert personDTOConvert;

    public AdministrativeDTO toDTO(Administrative administrative) {
        AdministrativeDTO result = mapper.map(administrative, AdministrativeDTO.class);
        result.setData(personDTOConvert.toDTO(administrative));
        return result;
    }

    public Administrative toEntity(AdministrativeDTO administrativeDTO) {
        Administrative result = mapper.map(administrativeDTO, Administrative.class);
        System.out.println("admin dto before add data"+result);
        personDTOConvert.addPersonalData(result, administrativeDTO.getData());
        System.out.println("admin dto after add data"+result);
        return result;
    }
}