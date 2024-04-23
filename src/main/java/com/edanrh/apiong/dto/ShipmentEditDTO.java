package com.edanrh.apiong.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShipmentEditDTO {
    private String codeSh;
    private LocalDateTime departureDate;
}
