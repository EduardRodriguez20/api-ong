package com.edanrh.apiong.controllers;

import com.edanrh.apiong.dto.DirectorDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.entities.Director;
import com.edanrh.apiong.service.DirectorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/director")
@AllArgsConstructor
public class DirectorController {

    private DirectorService directorService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() throws ContentNullException {
        Map<String,Object> response=new HashMap<>();
        List<DirectorDTO> users = directorService.findAll();
        response.put("directors", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{document}")
    public ResponseEntity<?> findByDocument(@RequestParam Long document) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        DirectorDTO resultDto = directorService.findByDocument(document);
        response.put("director", resultDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody DirectorDTO directorDTO) throws NotFoundException, DuplicateCreationException {
        Map<String,Object> response=new HashMap<>();
        try{
            DirectorDTO resultDto = directorService.save(directorDTO);
            response.put("message","Director saved successfully");
            response.put("director", resultDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (DataAccessException e){
            response.put("message","Error inserting to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{document}")
    public ResponseEntity<?> edit(@Valid @RequestBody DirectorDTO directorDTO, @RequestParam Long document) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        try{
            directorService.edit(document ,directorDTO);
            response.put("message","Director edited successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error inserting to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{document}")
    public ResponseEntity<?> delete(@RequestParam Long document) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        try{
            directorService.delete(document);
            response.put("message","Director deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error inserting to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
