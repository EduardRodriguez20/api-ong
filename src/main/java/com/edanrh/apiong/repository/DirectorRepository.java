package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Director;

import java.util.Optional;

public interface DirectorRepository extends CrudRepository<Director, Long>{
    @Query("SELECT a FROM directors a JOIN a.person p WHERE p.document = ?1")
    Optional<Director> findByDocument(Long document);
}
