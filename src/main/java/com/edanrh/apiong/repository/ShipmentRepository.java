package com.edanrh.apiong.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Shipment;

import java.util.Optional;

public interface ShipmentRepository extends CrudRepository<Shipment, Long>{
    Optional<Shipment> findByCodeShp (String codeShp);

    @Query("SELECT s FROM shipment s WHERE s.shelter.codeSh = ?1 LIMIT 1")
    Optional<Shipment> findByCodeSh (String codeSh);
}
