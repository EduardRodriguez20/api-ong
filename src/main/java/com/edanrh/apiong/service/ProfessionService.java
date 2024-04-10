package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.ProfessionDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;

import java.util.List;

public interface ProfessionService {
    List<ProfessionDTO> findAll() throws ContentNullException;
    ProfessionDTO save(ProfessionDTO professionDTO) throws DuplicateCreationException;
    boolean edit(String codePr, ProfessionDTO professionDTO) throws NotFoundException;
    boolean delete(String codePr) throws NotFoundException;
}
