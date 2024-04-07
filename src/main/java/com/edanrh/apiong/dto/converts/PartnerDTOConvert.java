package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.PartnerDTO;
import com.edanrh.apiong.repository.entities.Partner;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PartnerDTOConvert {
    
    private ModelMapper mapper;

    public PartnerDTO toDTO(Partner partern){
        return mapper.map(partern, PartnerDTO.class);
    }

    public Partner toEntity(PartnerDTO partnerDTO){
        return mapper.map(partnerDTO, Partner.class);
    }
}
