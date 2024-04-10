package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.SanitaryDTO;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;

import java.util.List;

public interface SanitaryService {
    List<SanitaryDTO> findAll() throws ContentNullException;
    SanitaryDTO save(SanitaryDTO sanitaryDTO) throws DuplicateCreationException, NotFoundException, BussinesRuleException;
    SanitaryDTO findByDocument(Long document) throws NotFoundException;
    boolean isAvailable(Long document) throws NotFoundException;
    boolean edit(Long document, SanitaryDTO sanitaryDTO) throws NotFoundException, DuplicateCreationException, BussinesRuleException;
    boolean delete(Long document) throws NotFoundException;
}
