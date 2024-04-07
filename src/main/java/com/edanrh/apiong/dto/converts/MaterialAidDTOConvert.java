package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.MaterialAidDTO;
import com.edanrh.apiong.repository.entities.MaterialAid;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MaterialAidDTOConvert {
    
    private ModelMapper mapper;

    public MaterialAidDTO toDTO(MaterialAid materialAid) {
        return mapper.map(materialAid, MaterialAidDTO.class);
    }

    public MaterialAid toEntity(MaterialAidDTO materialDTO) {
        return mapper.map(materialDTO, MaterialAid.class);
    }
}
