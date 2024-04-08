package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.MaterialAidDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.entities.Shipment;

import java.util.List;

public interface MaterialAidService {
    List<MaterialAidDTO> findAll() throws ContentNullException;
    MaterialAidDTO save(MaterialAidDTO materialAidDTO, Shipment shipment) throws NotFoundException;

}
