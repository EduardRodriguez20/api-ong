package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.CityDTO;

import java.util.List;

public interface CityService {
    List<CityDTO> findAll();
    CityDTO save(CityDTO cityDTO);
    boolean edit(CityDTO cityDTO);
    boolean delete(CityDTO cityDTO);
}
