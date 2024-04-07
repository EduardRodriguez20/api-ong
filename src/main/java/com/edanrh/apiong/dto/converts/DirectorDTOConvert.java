package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.DirectorDTO;
import com.edanrh.apiong.repository.entities.Director;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DirectorDTOConvert {
    
    private ModelMapper mapper;
    private PersonDTOConvert personDTOConvert;

    public DirectorDTO toDTO(Director director) {
        DirectorDTO dto = mapper.map(director, DirectorDTO.class);
        dto.setData(personDTOConvert.toDTO(director));
        return dto;
    }

    public Director toEntity(DirectorDTO directorDTO) {
        Director director = mapper.map(directorDTO, Director.class);
        System.out.println("director dto before add data"+director);
        personDTOConvert.addPersonalData(director, directorDTO.getData());
        System.out.println("director dto after add data"+director);
        return director;
    }
}
