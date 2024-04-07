package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.ShelterDTO;

import java.util.List;

public interface ShelterService {
    List<ShelterDTO> findAll();
    ShelterDTO save(ShelterDTO shelterDTO);
    ShelterDTO findByCodeSh(String codeSh);
    boolean deleteByCodeSh(String codeSh);
}
