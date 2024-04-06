package com.edanrh.apiong.repository;

import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Profession;

public interface ProfessionRepository extends CrudRepository<Profession, Long>{
    
}
