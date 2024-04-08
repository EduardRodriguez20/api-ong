package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Partner;

import java.util.Optional;

public interface PartnerRepository extends CrudRepository<Partner, Long>{
    @Query("SELECT a FROM partners a JOIN a.person p WHERE p.document = ?1")
    Optional<Partner> findByDocument(Long document);

    @Query("SELECT p FROM partners p WHERE p.headquarter.codeHq = ?1 LIMIT 1")
    Optional<Partner> findByCodeHq(String codeHq);
}
