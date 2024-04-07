package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.MaterialDTO;
import com.edanrh.apiong.repository.entities.Material;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MaterialDTOConvert {
    
    private ModelMapper mapper;

    public MaterialDTO toDTO(Material material){
        return mapper.map(material, MaterialDTO.class);
    }

    public Material toEntity(MaterialDTO materialDTO){
        return mapper.map(materialDTO, Material.class);
    }
}
