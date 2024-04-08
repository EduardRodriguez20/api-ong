package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.DirectorDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;

import java.util.List;

public interface DirectorService {
    List<DirectorDTO> findAll() throws ContentNullException;
    DirectorDTO findByDocument(Long document) throws NotFoundException;
    DirectorDTO save(DirectorDTO directorDTO) throws NotFoundException, DuplicateCreationException;
    boolean edit(Long document, DirectorDTO directorDTO) throws NotFoundException;
    boolean delete(Long document) throws NotFoundException;
}
