package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.ShelterDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.ReferencedEntityException;

import java.util.List;

public interface ShelterService {
    List<ShelterDTO> findAll() throws ContentNullException;
    ShelterDTO save(ShelterDTO shelterDTO) throws NotFoundException;
    ShelterDTO findByCodeSh(String codeSh) throws NotFoundException;
    boolean edit(String codeSh, ShelterDTO shelterDTO) throws NotFoundException;
    boolean deleteByCodeSh(String codeSh) throws NotFoundException, ReferencedEntityException;
}
