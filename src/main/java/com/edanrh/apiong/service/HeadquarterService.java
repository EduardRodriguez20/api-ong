package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.HeadquarterDTO;

import java.util.List;

public interface HeadquarterService {
    List<HeadquarterDTO> findAll();
    HeadquarterDTO save(HeadquarterDTO headquarterDTO);
    HeadquarterDTO findByCodeHq(String codeHq);
    boolean edit(String codeHq, HeadquarterDTO headquarterDTO);
    boolean deleteByCodeHq(String codeHq);
}
