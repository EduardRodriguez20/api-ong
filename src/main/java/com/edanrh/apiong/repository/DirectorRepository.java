package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Director;

import java.util.Optional;

public interface DirectorRepository extends CrudRepository<Director, Long>{
    @Query("SELECT d FROM directors d JOIN d.person p WHERE p.document = ?1")
    Optional<Director> findByDocument(Long document);

    @Query("SELECT d FROM directors d WHERE d.headquarter.codeHq = ?1")
    Optional<Director> findByCodeHq(String codeHq);

}
