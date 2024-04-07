package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.MaterialAidDTO;

import java.util.List;

public interface MaterialAidService {
    List<MaterialAidDTO> findAll();
    MaterialAidDTO save(MaterialAidDTO materialAidDTO);

}
