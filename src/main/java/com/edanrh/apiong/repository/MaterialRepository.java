package com.edanrh.apiong.repository;

import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Material;

public interface MaterialRepository extends CrudRepository<Material, Long>{
    
}
