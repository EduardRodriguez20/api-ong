package com.edanrh.apiong.dto.converts;

import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.MaterialAidDTO;
import com.edanrh.apiong.repository.entities.MaterialAid;

@Component
public class MaterialAidDTOConvert {
    
    public MaterialAidDTO toDTO(MaterialAid materialAid) {
        MaterialAidDTO dto = new MaterialAidDTO();
        dto.setCodeHq(materialAid.getHeadquarter().getCodeHq());
        return dto;
    }
}
