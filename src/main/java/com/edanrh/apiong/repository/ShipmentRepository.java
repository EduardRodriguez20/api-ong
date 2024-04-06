package com.edanrh.apiong.repository;

import org.springframework.data.repository.CrudRepository;

import com.edanrh.apiong.repository.entities.Shipment;

public interface ShipmentRepository extends CrudRepository<Shipment, Long>{
    
}
