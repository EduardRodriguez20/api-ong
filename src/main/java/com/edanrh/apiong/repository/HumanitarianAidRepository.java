package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.HumanitarianAid;

import java.util.Optional;

public interface HumanitarianAidRepository extends CrudRepository<HumanitarianAid, Long>{
    @Query("SELECT h FROM humanitarian_aid h WHERE h.headquarter.codeHq = ?1 LIMIT 1")
    Optional<HumanitarianAid> findByCodeHq(String codeHq);
}
