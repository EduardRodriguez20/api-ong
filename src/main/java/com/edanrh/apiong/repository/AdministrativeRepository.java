package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Administrative;

import java.util.Optional;

public interface AdministrativeRepository extends CrudRepository<Administrative, Long>{
    @Query("SELECT a FROM administrative a JOIN a.person p WHERE p.document = ?1")
    Optional<Administrative> findByDocument(Long document);
}
