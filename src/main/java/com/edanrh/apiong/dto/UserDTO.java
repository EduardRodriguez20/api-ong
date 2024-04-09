package com.edanrh.apiong.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String password;
    private List<String> roles = new ArrayList<>();
}
