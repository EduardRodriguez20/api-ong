package com.edanrh.apiong.service.impl;

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
import com.edanrh.apiong.repository.entities.Headquarter;
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
            throw new ContentNullException("code", "There isn't cities", HttpStatus.NO_CONTENT);
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
            throw new DuplicateCreationException("code", "City already exists", HttpStatus.CONFLICT);
        }else {
            City saved = cityRepository.save(dtoConvert.toEntity(cityDTO));
            return dtoConvert.toDTO(saved);
        }
    }

    @Override
    public boolean edit(CityDTO cityDTO) throws NotFoundException {
        Optional<City> city = cityRepository.findByName(cityDTO.getName());
        if (city.isPresent()) {
            city.get().setName(cityDTO.getName());
            city.get().setDepartment(cityDTO.getDepartment());
            cityRepository.save(city.get());
            return true;
        }else {
            throw new NotFoundException("code", "city invalid, don't exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean delete(CityDTO cityDTO) throws NotFoundException, ReferencedEntityException {
        Optional<City> city = cityRepository.findByName(cityDTO.getName());
        if (city.isPresent()) {
            if (shelterRepository.existsByCityName(cityDTO.getName())){
                throw new ReferencedEntityException("code", "City has shelters linked", HttpStatus.CONFLICT);
            }else if (headquarterRepository.existsByCityName(cityDTO.getName())){
                throw new ReferencedEntityException("code", "City has headquarters linked", HttpStatus.CONFLICT);
            }else {
                cityRepository.delete(city.get());
                return true;
            }
        }else {
            throw new NotFoundException("code", "city invalid, don't exist", HttpStatus.NOT_FOUND);
        }
    }
}
