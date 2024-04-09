package com.edanrh.apiong.controllers;

import com.edanrh.apiong.dto.SanitaryDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.service.SanitaryService;
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
@RequestMapping("/")
@AllArgsConstructor
public class SanitaryController {

    private SanitaryService sanitaryService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() throws ContentNullException {
        Map<String,Object> response=new HashMap<>();
        List<SanitaryDTO> result = sanitaryService.findAll();
        response.put("Sanitaries", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByDocument(@RequestParam Long document) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        SanitaryDTO result = sanitaryService.findByDocument(document);
        response.put("Sanitary", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody SanitaryDTO sanitaryDTO) throws NotFoundException, DuplicateCreationException {
        Map<String,Object> response=new HashMap<>();
        try{
            SanitaryDTO result = sanitaryService.save(sanitaryDTO);
                response.put("message", "Sanitary saved successfully");
            response.put("Sanitary", result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e){
            response.put("message","Error saving to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody SanitaryDTO sanitaryDTO, @RequestParam Long document) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        try{
            sanitaryService.edit(document, sanitaryDTO);
            response.put("message","Sanitary edited successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error editing to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@RequestParam Long id) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        try{
            sanitaryService.delete(id);
            response.put("message","Delete successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error deleting to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
