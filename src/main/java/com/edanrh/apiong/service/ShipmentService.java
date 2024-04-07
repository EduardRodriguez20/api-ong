package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.ShipmentDTO;

import java.util.List;

public interface ShipmentService {
    List<ShipmentDTO> findAll();
    ShipmentDTO save(ShipmentDTO shipmentDTO);
    boolean edit(String codeShp, ShipmentDTO shipmentDTO);
    boolean deleteByCodeShp(String codeShp);
}
