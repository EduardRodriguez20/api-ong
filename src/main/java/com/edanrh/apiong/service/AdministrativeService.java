package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.AdministrativeDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.entities.Administrative;

import java.util.List;

public interface AdministrativeService {
    List<AdministrativeDTO> findAll() throws ContentNullException;
    AdministrativeDTO findByDocument(Long document) throws NotFoundException;
    AdministrativeDTO save(AdministrativeDTO administrativeDTO) throws NotFoundException;
    boolean edit(Long document, AdministrativeDTO administrativeDTO) throws NotFoundException;
    boolean delete(Long document) throws NotFoundException;
}
