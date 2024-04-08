package com.edanrh.apiong.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PartnerDTO {
    
    private PersonDTO data;
    private String codeHq;
    private Long bankAccount;
    private String fee;
    private LocalDate paymentDate;

}
