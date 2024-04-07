package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.ProfessionDTO;

import java.util.List;

public interface ProfessionService {
    List<ProfessionDTO> findAll();
    ProfessionDTO save(ProfessionDTO professionDTO);
    boolean edit(ProfessionDTO professionDTO);
    boolean delete(ProfessionDTO professionDTO);
}
