package com.edanrh.apiong.controllers;

import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.service.PartnerService;
import com.edanrh.apiong.service.ReportsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportsController {

    private ReportsService reportsService;

    @GetMapping("/partners")
    public ResponseEntity<?> findPartners(){
        try {
            return ResponseEntity.ok(reportsService.findPartners());
        } catch (ContentNullException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/headquarters")
    public ResponseEntity<?> findHeadquarterReports(){
        try {
            return ResponseEntity.ok(reportsService.findHeadquarterReports());
        } catch (ContentNullException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/sanitaries")
    public ResponseEntity<?> findSanitaries(){
        try {
            return ResponseEntity.ok(reportsService.findSanitaryReports());
        } catch (ContentNullException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/administrative")
    public ResponseEntity<?> findAdministrative(){
        try {
            return ResponseEntity.ok(reportsService.findAdministrativeReports());
        } catch (ContentNullException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/materialAid")
    public ResponseEntity<?> findMaterialAid(){
        try {
            return ResponseEntity.ok(reportsService.findMaterialAidReports());
        } catch (ContentNullException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/humanitarianAid")
    public ResponseEntity<?> findHumanitarianAid(){
        try {
            return ResponseEntity.ok(reportsService.findHumanitarianAidReports());
        } catch (ContentNullException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
