package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Sanitary;

import java.util.Optional;

public interface SanitaryRepository extends CrudRepository<Sanitary, Long>{
    @Query("SELECT a FROM sanitaries a JOIN a.person p WHERE p.document = ?1")
    Optional<Sanitary> findByDocument(Long document);
}
