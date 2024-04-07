package com.edanrh.apiong.dto;

import lombok.Data;

@Data
public class SanitaryDTO {
    
    private PersonDTO data;
    private String codePr;
    private Boolean isAvailable;
}
