package com.edanrh.apiong.services;

import java.util.List;

import com.edanrh.apiong.repository.entities.UserEntity;

public interface UserService {
    UserEntity save(UserEntity user);
    UserEntity findByEmail(String email);
    List<UserEntity> findAll();
    boolean edit(String email, UserEntity user);
    boolean deleteByEmail(String email);
}

