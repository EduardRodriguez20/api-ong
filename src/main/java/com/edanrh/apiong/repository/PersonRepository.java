package com.edanrh.apiong.repository;

import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Person;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long>{
    Optional<Person> findByEmail(String email);
}
