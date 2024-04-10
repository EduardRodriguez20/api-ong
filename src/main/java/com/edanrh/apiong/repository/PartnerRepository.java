package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Partner;

import java.util.List;
import java.util.Optional;

public interface PartnerRepository extends CrudRepository<Partner, Long>{
    @Query("SELECT p FROM Partner p WHERE p.documentNumber = ?1")
    Optional<Partner> findByDocument(Long document);

    @Query("SELECT p FROM Partner p WHERE p.headquarter.codeHq = ?1")
    Optional<Partner> findFirstByCodeHq(String codeHq);

    @Query("SELECT p FROM Partner p WHERE p.fee.name = ?1")
    List<Partner> findByFeeName(String name);

    @Query("SELECT p FROM Partner p WHERE p.fee.name = ?1")
    Optional<Partner> findFirstByNameFee(String name);
}
