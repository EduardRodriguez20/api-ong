package com.edanrh.apiong.repository;

import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.RoleEntity;

public interface RepositoryRole extends CrudRepository<RoleEntity, Long>{
    
}
