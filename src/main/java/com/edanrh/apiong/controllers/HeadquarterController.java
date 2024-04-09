package com.edanrh.apiong.controllers;

import com.edanrh.apiong.dto.HeadquarterDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.ReferencedEntityException;
import com.edanrh.apiong.service.HeadquarterService;
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
@RequestMapping("/headquarter")
@AllArgsConstructor
public class HeadquarterController {

    private HeadquarterService headquarterService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() throws ContentNullException {
        Map<String,Object> response = new HashMap<>();
        List<HeadquarterDTO> result = headquarterService.findAll();
        response.put("Headquarters", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{codeHq}")
    public ResponseEntity<?> findByCodeHq(@RequestParam String codeHq) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        try{
            HeadquarterDTO headquarter = headquarterService.findByCodeHq(codeHq);
            response.put("Headquarter", headquarter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody HeadquarterDTO headquarterDTO) throws ContentNullException, DuplicateCreationException, NotFoundException {
        Map<String,Object> response=new HashMap<>();
        try{
            HeadquarterDTO headquarter = headquarterService.save(headquarterDTO);
            response.put("message", "Headquarter saved successfully");
            response.put("Headquarter", headquarter);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{codeHq}")
    public ResponseEntity<?> edit(@Valid @RequestBody HeadquarterDTO headquarterDTO, @RequestParam String codeHq) throws ContentNullException, DuplicateCreationException, NotFoundException {
        Map<String,Object> response=new HashMap<>();
        try{
            headquarterService.edit(codeHq, headquarterDTO);
            response.put("message", "Headquarter edited successfully");
            response.put("Headquarter", headquarterDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{codeHq}")
    public ResponseEntity<?> delete(@RequestParam String codeHq) throws NotFoundException, ReferencedEntityException {
        Map<String,Object> response=new HashMap<>();
        try{
            headquarterService.deleteByCodeHq(codeHq);
            response.put("message", "Headquarter deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
