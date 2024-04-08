package com.edanrh.apiong.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MaterialAidDTO {

    private String codeShp;
    private String codeHq;
    private List<MaterialDTO> material = new ArrayList<>();
}
