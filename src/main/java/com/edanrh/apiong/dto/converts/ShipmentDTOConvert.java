package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.ShipmentDTO;
import com.edanrh.apiong.repository.entities.Shipment;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ShipmentDTOConvert {
    
    private ModelMapper mapper;

    public ShipmentDTO toDTO(Shipment shipment){
        return mapper.map(shipment, ShipmentDTO.class);
    }

    public Shipment toEntity(ShipmentDTO shipmentDTO){
        return mapper.map(shipmentDTO, Shipment.class);
    }

}
