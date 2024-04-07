package com.edanrh.apiong.dto;

import java.util.List;

import lombok.Data;

@Data
public class MaterialAidDTO {
    
    private String codeHq;
    private List<MaterialDTO> material;
}
