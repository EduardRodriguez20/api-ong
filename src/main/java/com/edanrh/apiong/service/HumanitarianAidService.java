package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.HumanitarianAidDTO;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.QuantityEntityException;
import com.edanrh.apiong.repository.entities.Shipment;

import java.util.List;

public interface HumanitarianAidService {
    List<HumanitarianAidDTO> findAll() throws ContentNullException;
    HumanitarianAidDTO save(HumanitarianAidDTO humanitarianAidDTO, Shipment shipment) throws NotFoundException, ContentNullException, QuantityEntityException, BussinesRuleException;
}
