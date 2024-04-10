package com.edanrh.apiong.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.UserEntity;

public interface RepositoryUser extends CrudRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> deleteByEmail(String email);
    
}
