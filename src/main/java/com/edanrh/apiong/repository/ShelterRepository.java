package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Shelter;
import org.springframework.data.repository.query.Param;

public interface ShelterRepository extends CrudRepository<Shelter, Long>{
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Shelter s WHERE s.city.name = :cityName")
    boolean existsByCityName(@Param("cityName") String cityName);
}
