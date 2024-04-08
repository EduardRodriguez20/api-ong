package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Sanitary;

import java.util.List;
import java.util.Optional;

public interface SanitaryRepository extends CrudRepository<Sanitary, Long>{
    @Query("SELECT a FROM sanitaries a JOIN a.person p WHERE p.document = ?1")
    Optional<Sanitary> findByDocument(Long document);

    @Query("SELECT s FROM sanitaries s WHERE s.headquarter.codeHq = ?1 LIMIT 1")
    Optional<Sanitary> findByCodeHq(String codeHq);

    @Query("SELECT s FROM sanitaries s WHERE s.profession.codePr = ?1 AND " +
            "s.headquarter.codeHq = ?2 AND s.isAvailable ORDER BY s.participation ASC")
    List<Sanitary> findByCodePrAndCodeHq(String codePr, String codeHq);
}
