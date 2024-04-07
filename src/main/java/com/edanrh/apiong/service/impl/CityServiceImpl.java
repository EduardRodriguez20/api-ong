package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.dto.CityDTO;
import com.edanrh.apiong.dto.converts.CityDTOConvert;
import com.edanrh.apiong.repository.CityRepository;
import com.edanrh.apiong.repository.entities.City;
import com.edanrh.apiong.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;
    private CityDTOConvert dtoConvert;

    @Override
    public List<CityDTO> findAll() {
        List<City> cities = (List<City>) cityRepository.findAll();
        List<CityDTO> citiesdto = new ArrayList<>();
        for (City city : cities) {
            citiesdto.add(dtoConvert.toDTO(city));
        }
        return citiesdto;
    }

    @Override
    public CityDTO save(CityDTO cityDTO) {
        Optional<City> city = cityRepository.findByName(cityDTO.getName());
        if (city.isEmpty()) {
            City saved = cityRepository.save(dtoConvert.toEntity(cityDTO));
            return dtoConvert.toDTO(saved);
        }
        return null;
    }

    @Override
    public boolean edit(CityDTO cityDTO) {
        Optional<City> city = cityRepository.findByName(cityDTO.getName());
        if (city.isPresent()) {
            city.get().setName(cityDTO.getName());
            city.get().setDepartment(cityDTO.getDepartment());
            cityRepository.save(city.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(CityDTO cityDTO) {
        Optional<City> city = cityRepository.findByName(cityDTO.getName());
        if (city.isPresent()) {
            cityRepository.delete(city.get());
            return true;
        }
        return false;
    }
}
