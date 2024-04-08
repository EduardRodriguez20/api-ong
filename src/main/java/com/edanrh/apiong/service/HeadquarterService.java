package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.HeadquarterDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.ReferencedEntityException;

import java.util.List;

public interface HeadquarterService {
    List<HeadquarterDTO> findAll() throws ContentNullException;
    HeadquarterDTO findByCodeHq(String codeHq) throws NotFoundException;
    HeadquarterDTO save(HeadquarterDTO headquarterDTO) throws NotFoundException, DuplicateCreationException;
    boolean edit(String codeHq, HeadquarterDTO headquarterDTO) throws NotFoundException;
    boolean deleteByCodeHq(String codeHq) throws NotFoundException, ReferencedEntityException;
}
