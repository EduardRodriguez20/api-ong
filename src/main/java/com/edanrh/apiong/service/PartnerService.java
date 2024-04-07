package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.PartnerDTO;

import java.util.List;

public interface PartnerService {
    List<PartnerDTO> findAll();
    PartnerDTO findByDocument (Long document);
    PartnerDTO save (PartnerDTO partner);
    boolean edit (Long document, PartnerDTO partner);
    boolean delete (Long document);
}
