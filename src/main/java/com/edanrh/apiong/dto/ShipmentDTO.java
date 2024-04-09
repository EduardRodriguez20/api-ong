package com.edanrh.apiong.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ShipmentDTO {

    private LocalDateTime departureDate;
    private String codeShp;
    private String codeSh;
    private List<HumanitarianAidDTO> humanAid;
    private List<MaterialAidDTO> materialAid;

}
