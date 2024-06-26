package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.ShipmentDTO;
import com.edanrh.apiong.dto.ShipmentEditDTO;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.QuantityEntityException;

import java.util.List;

public interface ShipmentService {
    List<ShipmentDTO> findAll() throws ContentNullException;
    ShipmentDTO save(ShipmentDTO shipmentDTO) throws NotFoundException, ContentNullException, QuantityEntityException, BussinesRuleException;
    boolean edit(String codeShp, ShipmentEditDTO shipmentDTO) throws NotFoundException, BussinesRuleException;
}
