package com.edanrh.apiong.controllers;

import com.edanrh.apiong.dto.ShipmentDTO;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.QuantityEntityException;
import com.edanrh.apiong.service.ShipmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shipment")
@AllArgsConstructor
public class ShipmentController {

    private ShipmentService shipmentService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() throws ContentNullException {
        Map<String,Object> response = new HashMap<>();
        List<ShipmentDTO> result = shipmentService.findAll();
        response.put("Shipments", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody ShipmentDTO shipmentDTO) throws
            NotFoundException, ContentNullException, QuantityEntityException, BussinesRuleException {
        Map<String,Object> response = new HashMap<>();
        try {
            ShipmentDTO result = shipmentService.save(shipmentDTO);
            response.put("message", "Shipment saved successfully");
            response.put("Shipment", result);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e){
            response.put("message", "Error saving to database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{codeShp}")
    public ResponseEntity<?> edit(@Valid @RequestBody ShipmentDTO shipmentDTO, @PathVariable String codeShp) throws NotFoundException {
        Map<String,Object> response = new HashMap<>();
        try {
            shipmentService.edit(codeShp, shipmentDTO);
            response.put("message", "Shipment edited successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e){
            response.put("message", "Error editing to database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
