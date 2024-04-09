package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.CityDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.ReferencedEntityException;

import java.util.List;

public interface CityService {
    List<CityDTO> findAll() throws ContentNullException;
    CityDTO save(CityDTO cityDTO) throws DuplicateCreationException;
    boolean edit(CityDTO cityDTO, String name) throws NotFoundException;
    boolean delete(String name) throws NotFoundException, ReferencedEntityException;
}
