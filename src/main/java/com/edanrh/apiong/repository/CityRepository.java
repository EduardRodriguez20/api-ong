package com.edanrh.apiong.repository;

import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.City;

import java.util.Optional;

public interface CityRepository extends CrudRepository<City, Long>{
    Optional<City> findByName(String name);
}
