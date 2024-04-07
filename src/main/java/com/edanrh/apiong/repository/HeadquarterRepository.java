package com.edanrh.apiong.repository;

import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Headquarter;

import java.util.Optional;

public interface HeadquarterRepository extends CrudRepository<Headquarter, Long>{
    Optional<Headquarter> findByCodeHq(String codeHq);
}
