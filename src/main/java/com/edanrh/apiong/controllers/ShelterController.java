package com.edanrh.apiong.controllers;

import com.edanrh.apiong.dto.ShelterDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.ReferencedEntityException;
import com.edanrh.apiong.service.ShelterService;
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
@RequestMapping("/shelter")
@AllArgsConstructor
public class ShelterController {

    private ShelterService shelterService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() throws ContentNullException {
        Map<String,Object> response = new HashMap<>();
        List<ShelterDTO> result = shelterService.findAll();
        response.put("Shelters", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{codeSh}")
    public ResponseEntity<?> findByCodeSh(@RequestParam String codeSh) throws NotFoundException {
        Map<String,Object> response = new HashMap<>();
        ShelterDTO result = shelterService.findByCodeSh(codeSh);
        response.put("Shelter", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody ShelterDTO shelterDTO) throws NotFoundException {
        Map<String,Object> response = new HashMap<>();
        try {
            ShelterDTO result = shelterService.save(shelterDTO);
            response.put("message", "Shelter saved successfully");
            response.put("Shelter", result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{codeSh}")
    public ResponseEntity<?> edit(@Valid @RequestBody ShelterDTO shelterDTO, @RequestParam String codeSh) throws NotFoundException {
        Map<String,Object> response = new HashMap<>();
        try {
            shelterService.edit(codeSh, shelterDTO);
            response.put("message", "Shelter edited successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{codeSh}")
    public ResponseEntity<?> delete(@RequestParam String codeSh) throws NotFoundException, ReferencedEntityException {
        Map<String,Object> response = new HashMap<>();
        try {
            shelterService.deleteByCodeSh(codeSh);
            response.put("message", "Shelter deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
