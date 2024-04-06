package com.edanrh.apiong.repository;

import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.City;

public interface CityRepository extends CrudRepository<City, Long>{
    
}
