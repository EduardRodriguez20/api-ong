package com.edanrh.apiong.controllers;

import com.edanrh.apiong.dto.CityDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.ReferencedEntityException;
import com.edanrh.apiong.service.CityService;
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
@RequestMapping("/city")
@AllArgsConstructor
public class CityController {

    private CityService cityService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() throws ContentNullException {
        Map<String,Object> response=new HashMap<>();
        List<CityDTO> cities = cityService.findAll();
        response.put("cities", cities);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody CityDTO cityDTO) throws ContentNullException, DuplicateCreationException {
        Map<String,Object> response=new HashMap<>();
        try{
            CityDTO city = cityService.save(cityDTO);
            response.put("message","Register successfully");
            response.put("city", city);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (DataAccessException e){
            response.put("message","Error inserting to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{name}")
    public ResponseEntity<?> update(@Valid @RequestBody CityDTO cityDTO, @RequestParam String name) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        try{
            cityService.edit(cityDTO, name);
            response.put("message","Edited successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error updating to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) throws NotFoundException, ReferencedEntityException {
        Map<String,Object> response=new HashMap<>();
        try{
            cityService.delete(name);
            response.put("message","Deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error deleting to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
