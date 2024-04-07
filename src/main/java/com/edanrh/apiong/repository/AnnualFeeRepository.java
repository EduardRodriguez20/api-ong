package com.edanrh.apiong.repository;

import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.AnnualFee;

import java.util.Optional;

public interface AnnualFeeRepository extends CrudRepository<AnnualFee, Long>{
    Optional<AnnualFee> findByName(String name);
}
