package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.AdministrativeDTO;

import java.util.List;

public interface AdministrativeService {
    List<AdministrativeDTO> findAll();
    AdministrativeDTO findByDocument(Long document);
    AdministrativeDTO save(AdministrativeDTO administrativeDTO);
    boolean edit(Long document, AdministrativeDTO administrativeDTO);
    boolean delete(Long document);
}
