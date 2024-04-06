package com.edanrh.apiong.repository;

import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long>{
    
}
