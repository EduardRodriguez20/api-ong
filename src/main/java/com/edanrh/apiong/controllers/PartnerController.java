package com.edanrh.apiong.controllers;

import com.edanrh.apiong.dto.PartnerDTO;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.service.PartnerService;
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
@RequestMapping("/partner")
@AllArgsConstructor
public class PartnerController {

    private PartnerService partnerService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() throws ContentNullException {
        Map<String,Object> response=new HashMap<>();
        List<PartnerDTO> users = partnerService.findAll();
        response.put("Partners", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{document}")
    public ResponseEntity<?> findByDocument(@RequestParam Long document) throws NotFoundException {
        Map<String,Object> response=new HashMap<>();
        PartnerDTO user = partnerService.findByDocument(document);
        response.put("Partner", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody PartnerDTO partnerDTO) throws DuplicateCreationException, NotFoundException, BussinesRuleException {
        Map<String,Object> response=new HashMap<>();
        try{
            PartnerDTO partner = partnerService.save(partnerDTO);
            response.put("message", "Partner saved successfully");
            response.put("Partner", partner);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error saving to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{document}")
    public ResponseEntity<?> edit(@Valid @RequestBody PartnerDTO partnerDTO, @RequestParam Long document) throws NotFoundException, DuplicateCreationException, BussinesRuleException {
        Map<String,Object> response=new HashMap<>();
        try{
            partnerService.edit(document,partnerDTO);
            response.put("message","Partner edited successfully");
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
            partnerService.delete(document);
            response.put("message", "Partner deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("message", "Error deleting to database");
            response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
