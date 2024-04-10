package com.edanrh.apiong.controllers;

import com.edanrh.apiong.dto.ProfessionDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.service.ProfessionService;
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
@RequestMapping("/profession")
@AllArgsConstructor
public class ProfessionController {

    private ProfessionService professionService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() throws ContentNullException {
        Map<String,Object> response=new HashMap<>();
        List<ProfessionDTO> professions = professionService.findAll();
        response.put("professions", professions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody ProfessionDTO professionDTO) throws DuplicateCreationException {
        Map<String,Object> response=new HashMap<>();
        try{
            ProfessionDTO result = professionService.save(professionDTO);
            response.put("message", "Profession saved successfully");
            response.put("profession", result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error saving to database");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{codePr}")
    public ResponseEntity<?> edit(@Valid @RequestBody ProfessionDTO professionDTO, String codePr) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        try{
            professionService.edit(codePr, professionDTO);
            response.put("message", "Profession edited successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error editing to database");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{codePr}")
    public ResponseEntity<?> delete(@PathVariable String codePr) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        try{
            professionService.delete(codePr);
            response.put("message", "Profession deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error deleting to database");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
