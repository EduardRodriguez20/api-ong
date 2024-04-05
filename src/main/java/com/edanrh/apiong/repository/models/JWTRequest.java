package com.edanrh.apiong.repository.models;

import lombok.Data;

@Data
public class JWTRequest {

    private String username;
    private String password;
}
