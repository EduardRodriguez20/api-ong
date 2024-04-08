package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Headquarter;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HeadquarterRepository extends CrudRepository<Headquarter, Long>{
    Optional<Headquarter> findByCodeHq(String codeHq);

    @Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM Headquarter h WHERE h.city.name = :cityName")
    boolean existsByCityName(@Param("cityName") String cityName);
}
