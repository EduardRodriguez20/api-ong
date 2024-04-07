package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.SanitaryDTO;

import java.util.List;

public interface SanitaryService {
    List<SanitaryDTO> findAll();
    SanitaryDTO save(SanitaryDTO sanitaryDTO);
    SanitaryDTO findByDocument(Long document);
    boolean edit(Long document, SanitaryDTO sanitaryDTO);
    boolean delete(Long document);
}
