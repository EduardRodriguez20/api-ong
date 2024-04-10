package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Sanitary;

import java.util.Optional;

public interface SanitaryRepository extends CrudRepository<Sanitary, Long>{
    @Query("SELECT s FROM Sanitary s WHERE s.documentNumber = ?1")
    Optional<Sanitary> findByDocument(Long document);

    @Query("SELECT s FROM Sanitary s WHERE s.headquarter.codeHq = ?1")
    Optional<Sanitary> findFirstByCodeHq(String codeHq);

} 
