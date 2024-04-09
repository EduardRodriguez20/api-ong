package com.edanrh.apiong.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class HumanitarianAidDTO {

    private String codeShp;
    private String codePr;
    private String codeHq;
    private Set<Long> documents = new HashSet<>();
    private int quantity = 0;
}
