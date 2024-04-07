package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.DirectorDTO;

import java.util.List;

public interface DirectorService {
    List<DirectorDTO> findAll();
    DirectorDTO findByDocument(Long document);
    DirectorDTO save(DirectorDTO directorDTO);
    boolean edit(Long document, DirectorDTO directorDTO);
    boolean delete(Long document);
}
