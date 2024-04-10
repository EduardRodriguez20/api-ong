package com.edanrh.apiong.repository;

import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Profession;

import java.util.Optional;

public interface ProfessionRepository extends CrudRepository<Profession, Long>{
    Optional<Profession> findByCodePr(String codePr);

    Optional<Profession> findByName(String name);
}
