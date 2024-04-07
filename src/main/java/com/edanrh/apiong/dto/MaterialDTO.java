package com.edanrh.apiong.dto;

import com.edanrh.apiong.resources.enums.MaterialType;

import lombok.Data;

@Data
public class MaterialDTO {
    
    private MaterialType material;
    private Double quantity;
    private String description;
}
