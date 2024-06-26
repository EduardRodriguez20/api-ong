package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.common.ErrorCode;
import com.edanrh.apiong.dto.CityDTO;
import com.edanrh.apiong.dto.converts.CityDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.ReferencedEntityException;
import com.edanrh.apiong.repository.CityRepository;
import com.edanrh.apiong.repository.HeadquarterRepository;
import com.edanrh.apiong.repository.ShelterRepository;
import com.edanrh.apiong.repository.entities.City;
import com.edanrh.apiong.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;
    private HeadquarterRepository headquarterRepository;
    private ShelterRepository shelterRepository;
    private CityDTOConvert dtoConvert;

    @Override
    public List<CityDTO> findAll() throws ContentNullException {
        List<City> cities = (List<City>) cityRepository.findAll();
        if (cities.isEmpty()){
            throw new ContentNullException(ErrorCode.CITY_CONTENT_NOT_FOUND, "There isn't cities", HttpStatus.NO_CONTENT);
        }else {
            List<CityDTO> citiesDto = new ArrayList<>();
            for (City city : cities) {
                citiesDto.add(dtoConvert.toDTO(city));
            }
            return citiesDto;
        }
    }

    @Override
    public CityDTO save(CityDTO cityDTO) throws DuplicateCreationException {
        Optional<City> city = cityRepository.findByName(cityDTO.getName());
        if (city.isPresent()){
            throw new DuplicateCreationException(ErrorCode.CITY_DUPLICATE_CREATION, "City already exists", HttpStatus.CONFLICT);
        }else {
            City saved = cityRepository.save(dtoConvert.toEntity(cityDTO));
            return dtoConvert.toDTO(saved);
        }
    }

    @Override
    public boolean edit(CityDTO cityDTO, String name) throws NotFoundException {
        Optional<City> city = cityRepository.findByName(name);
        if (city.isPresent()) {
            city.get().setName(cityDTO.getName());
            city.get().setDepartment(cityDTO.getDepartment());
            cityRepository.save(city.get());
            return true;
        }else {
            throw new NotFoundException(ErrorCode.CITY_NAME_NOT_FOUND, "city invalid, don't exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean delete(String name) throws NotFoundException, ReferencedEntityException {
        Optional<City> city = cityRepository.findByName(name);
        if (city.isPresent()) {
            if (shelterRepository.existsByCityName(city.get().getName())){
                throw new ReferencedEntityException(ErrorCode.CITY_REFERENCED_EXIST, "City has shelters linked", HttpStatus.CONFLICT);
            }else if (headquarterRepository.existsByCityName(city.get().getName())){
                throw new ReferencedEntityException(ErrorCode.CITY_REFERENCED_EXIST, "City has headquarters linked", HttpStatus.CONFLICT);
            }else {
                cityRepository.delete(city.get());
                return true;
            }
        }else {
            throw new NotFoundException(ErrorCode.CITY_NAME_NOT_FOUND, "city invalid, don't exist", HttpStatus.NOT_FOUND);
        }
    }
}
