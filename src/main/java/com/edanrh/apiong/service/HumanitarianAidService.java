package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.HumanitarianAidDTO;

import java.util.List;

public interface HumanitarianAidService {
    List<HumanitarianAidDTO> findAll();
    HumanitarianAidDTO save(HumanitarianAidDTO humanitarianAidDTO);
    boolean edit(HumanitarianAidDTO humanitarianAidDTO);
    boolean delete(HumanitarianAidDTO humanitarianAidDTO);
}
