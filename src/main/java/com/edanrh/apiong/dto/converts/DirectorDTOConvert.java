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

    public DirectorDTO toDTO(Director director) {
        return mapper.map(director, DirectorDTO.class);
    }

    public Director toEntity(DirectorDTO directorDTO) {
        return mapper.map(directorDTO, Director.class);
    }
}
