package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.AnnualFeeDTO;

import java.util.List;

public interface AnnualFeeService {
    List<AnnualFeeDTO> findAll();
    AnnualFeeDTO save(AnnualFeeDTO annualFeeDTO);
    boolean edit(String name, AnnualFeeDTO annualFeeDTO);
    boolean delete(String name);
}
