package com.edanrh.apiong.dto.converts;

import com.edanrh.apiong.repository.entities.Material;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.MaterialAidDTO;
import com.edanrh.apiong.repository.entities.MaterialAid;

@Component
@AllArgsConstructor
public class MaterialAidDTOConvert {

    private MaterialDTOConvert materialDTOConvert;
    
    public MaterialAidDTO toDTO(MaterialAid materialAid) {
        MaterialAidDTO dto = new MaterialAidDTO();
        dto.setCodeHq(materialAid.getHeadquarter().getCodeHq());
        dto.setCodeShp(materialAid.getShipment().getCodeShp());
        for (Material material : materialAid.getMaterial()){
            dto.getMaterial().add(materialDTOConvert.toDTO(material));
        }
        return dto;
    }
}
