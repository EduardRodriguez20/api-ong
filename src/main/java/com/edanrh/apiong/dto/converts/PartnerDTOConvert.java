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
    private PersonDTOConvert personDTOConvert;

    public PartnerDTO toDTO(Partner partner){
        PartnerDTO dto = mapper.map(partner, PartnerDTO.class);
        dto.setData(personDTOConvert.toDTO(partner));
        return dto;
    }

    public Partner toEntity(PartnerDTO partnerDTO){
        Partner partner = mapper.map(partnerDTO, Partner.class);
        System.out.println("partner dto before add data"+partner);
        personDTOConvert.addPersonalData(partner, partnerDTO.getData());
        System.out.println("partner dto after add data"+partner);
        return partner;
    }
}
