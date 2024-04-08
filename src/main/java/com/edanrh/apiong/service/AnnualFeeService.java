package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.AnnualFeeDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;

import java.util.List;

public interface AnnualFeeService {
    List<AnnualFeeDTO> findAll() throws ContentNullException;
    AnnualFeeDTO save(AnnualFeeDTO annualFeeDTO) throws DuplicateCreationException;
    boolean edit(String name, AnnualFeeDTO annualFeeDTO) throws NotFoundException;
    boolean delete(String name) throws NotFoundException;
}
