package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.MaterialDTO;

import java.util.List;

public interface MaterialService {
    List<MaterialDTO> findAll();
    MaterialDTO save(MaterialDTO materialDTO);
}
