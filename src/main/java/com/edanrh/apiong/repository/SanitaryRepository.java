package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Sanitary;

import java.util.List;
import java.util.Optional;

public interface SanitaryRepository extends CrudRepository<Sanitary, Long>{
    @Query("SELECT s FROM Sanitary s WHERE s.documentNumber = ?1")
    Optional<Sanitary> findByDocument(Long document);

    @Query("SELECT s FROM Sanitary s WHERE s.profession.codePr = ?1 AND s.headquarter.codeHq = ?2")
    List<Sanitary> findByCodePrAndCodeHq(String codePr, String codeHq);

    @Query("SELECT s FROM Sanitary s WHERE s.headquarter.codeHq = ?1")
    Optional<Sanitary> findFirstByCodeHq(String codeHq);

} 
