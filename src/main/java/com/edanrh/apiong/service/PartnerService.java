package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.PartnerDTO;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;

import java.util.List;

public interface PartnerService {
    List<PartnerDTO> findAll() throws ContentNullException;
    PartnerDTO findByDocument (Long document) throws NotFoundException;
    PartnerDTO save (PartnerDTO partner) throws DuplicateCreationException, NotFoundException, BussinesRuleException;
    boolean edit (Long document, PartnerDTO partner) throws NotFoundException, DuplicateCreationException, BussinesRuleException;
    boolean delete (Long document) throws NotFoundException;
}
