package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.AdministrativeDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;

import java.util.List;

public interface AdministrativeService {
    List<AdministrativeDTO> findAll() throws ContentNullException;
    AdministrativeDTO findByDocument(Long document) throws NotFoundException;
    AdministrativeDTO save(AdministrativeDTO administrativeDTO) throws NotFoundException, DuplicateCreationException;
    boolean edit(Long document, AdministrativeDTO administrativeDTO) throws NotFoundException, DuplicateCreationException;
    boolean delete(Long document) throws NotFoundException;
}
