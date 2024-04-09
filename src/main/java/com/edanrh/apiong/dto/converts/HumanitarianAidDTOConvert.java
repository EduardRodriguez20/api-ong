package com.edanrh.apiong.dto.converts;

import com.edanrh.apiong.repository.entities.DocSanitarySent;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.HumanitarianAidDTO;
import com.edanrh.apiong.repository.entities.HumanitarianAid;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HumanitarianAidDTOConvert {
    
    private ModelMapper mapper;

    public HumanitarianAidDTO toDTO(HumanitarianAid humanitarianAid) {
        HumanitarianAidDTO dto = mapper.map(humanitarianAid, HumanitarianAidDTO.class);
        dto.setCodeShp(humanitarianAid.getShipment().getCodeShp());
        dto.setCodeHq(humanitarianAid.getHeadquarter().getCodeHq());
        dto.setCodePr(humanitarianAid.getProfession().getCodePr());
        for (DocSanitarySent doc : humanitarianAid.getDocumentsSanitaries()){
            dto.getDocuments().add(doc.getDocumentSanitary());
        }
        return dto;
    }

    public HumanitarianAid toEntity(HumanitarianAidDTO humanitarianAidDTO) {
        return mapper.map(humanitarianAidDTO, HumanitarianAid.class);
    }
}
