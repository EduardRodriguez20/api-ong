package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.MaterialAid;

import java.util.Optional;

public interface MaterialAidRepository extends CrudRepository<MaterialAid, Long>{
    @Query("SELECT m FROM MaterialAid m WHERE m.headquarter.codeHq = ?1")
    Optional<MaterialAid> findFirstByCodeHq(String codeHq);
}
