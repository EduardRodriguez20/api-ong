package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Partner;

import java.util.Optional;

public interface PartnerRepository extends CrudRepository<Partner, Long>{
    @Query("SELECT a FROM partners a JOIN a.person p WHERE p.document = ?1")
    Optional<Partner> findByDocument(Long document);
}
