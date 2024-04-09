package com.edanrh.apiong.controllers;

import com.edanrh.apiong.dto.AdministrativeDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.service.AdministrativeService;
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
@RequestMapping("/administrative")
@AllArgsConstructor
public class AdministrativeController {

    private AdministrativeService administrativeService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() throws ContentNullException {
        Map<String,Object> response = new HashMap<>();
        List<AdministrativeDTO> result = administrativeService.findAll();
        response.put("Administrative", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{document}")
    public ResponseEntity<?> findByDocument(@RequestParam Long document) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        AdministrativeDTO result = administrativeService.findByDocument(document);
        response.put("Administrative", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody AdministrativeDTO administrativeDTO) throws NotFoundException, DuplicateCreationException {
        Map<String,Object> response=new HashMap<>();
        try{
            AdministrativeDTO result = administrativeService.save(administrativeDTO);
            response.put("message", "Administrative saved successfully");
            response.put("Administrative", result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error saving to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{document}")
    public ResponseEntity<?> edit(@Valid @RequestBody AdministrativeDTO administrativeDTO, @RequestParam Long document) throws NotFoundException, DuplicateCreationException {
        Map<String,Object> response=new HashMap<>();
        try{
            administrativeService.edit(document,administrativeDTO);
            response.put("message","Edit successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error editing to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{document}")
    public ResponseEntity<?> delete(@RequestParam Long document) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        try{
            administrativeService.delete(document);
            response.put("message","Delete successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error deleting to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
