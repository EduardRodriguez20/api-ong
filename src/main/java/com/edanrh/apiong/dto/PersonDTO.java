package com.edanrh.apiong.dto;

import com.edanrh.apiong.resources.enums.DocumentType;
import com.edanrh.apiong.resources.enums.Gender;

import lombok.Data;

@Data
public class PersonDTO {
    private String name;
    private String surname;
    private DocumentType documentType;
    private Long documentNumber;
    private Gender gender;
    private String email;
}
