package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.CityDTO;
import com.edanrh.apiong.repository.entities.City;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CityDTOConvert {
    
    private ModelMapper mapper;

    public CityDTO toDTO(City city) {
        return mapper.map(city, CityDTO.class);
    }

    public City toEntity(CityDTO cityDTO) {
        return mapper.map(cityDTO, City.class);
    }
}
