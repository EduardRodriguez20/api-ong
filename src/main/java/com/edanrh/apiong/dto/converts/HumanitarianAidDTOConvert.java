package com.edanrh.apiong.dto.converts;

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
        return mapper.map(humanitarianAid, HumanitarianAidDTO.class);
    }

    public HumanitarianAid toEntity(HumanitarianAidDTO humanitarianAidDTO) {
        return mapper.map(humanitarianAidDTO, HumanitarianAid.class);
    }
}
