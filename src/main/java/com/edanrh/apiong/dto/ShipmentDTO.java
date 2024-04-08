package com.edanrh.apiong.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ShipmentDTO {

    private Date departureDate;
    private String codeSh;
    private List<HumanitarianAidDTO> humanAidDTOs;
    private List<MaterialAidDTO> materialAidDTOs;

}
