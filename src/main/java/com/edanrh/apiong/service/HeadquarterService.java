package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.HeadquarterDTO;

import java.util.List;

public interface HeadquarterService {
    List<HeadquarterDTO> findAll();
    HeadquarterDTO findByCodeHq(String codeHq);
    HeadquarterDTO save(HeadquarterDTO headquarterDTO);
    boolean edit(String codeHq, HeadquarterDTO headquarterDTO);
    boolean deleteByCodeHq(String codeHq);
}
