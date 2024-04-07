package com.edanrh.apiong.dto;

import lombok.Data;

@Data
public class PartnerDTO {
    
    private PersonDTO data;
    private Long bankAccount;
    private String fee;

}
