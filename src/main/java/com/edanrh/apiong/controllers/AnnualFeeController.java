package com.edanrh.apiong.controllers;

import com.edanrh.apiong.dto.AnnualFeeDTO;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.ReferencedEntityException;
import com.edanrh.apiong.service.AnnualFeeService;
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
@RequestMapping("/annualFee")
@AllArgsConstructor
public class AnnualFeeController {

    private AnnualFeeService annualFeeService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() throws ContentNullException {
        Map<String,Object> response=new HashMap<>();
        List<AnnualFeeDTO> users = annualFeeService.findAll();
        response.put("annualFees", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody AnnualFeeDTO annualFeeDTO) throws DuplicateCreationException {
        Map<String,Object> response=new HashMap<>();
        try{
            AnnualFeeDTO result = annualFeeService.save(annualFeeDTO);
            response.put("message","Annual Fee saved successfully");
            response.put("annualFee", result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error inserting to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{name}")
    public ResponseEntity<?> edit(@Valid @RequestBody AnnualFeeDTO annualFeeDTO, @PathVariable String name) throws DuplicateCreationException, NotFoundException {
        Map<String,Object> response=new HashMap<>();
        try{
            annualFeeService.edit(name, annualFeeDTO);
            response.put("message","Annual Fee edited successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error editing to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) throws NotFoundException, ReferencedEntityException {
        Map<String,Object> response=new HashMap<>();
        try{
            annualFeeService.delete(name);
            response.put("message","Annual Fee deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message","Error deleting to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
