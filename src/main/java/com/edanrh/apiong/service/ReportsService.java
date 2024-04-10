package com.edanrh.apiong.service;

import com.edanrh.apiong.dto.*;
import com.edanrh.apiong.dto.converts.HeadReportDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.repository.DirectorRepository;
import com.edanrh.apiong.repository.entities.Director;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportsService {

    private PartnerService partnerService;
    private HeadquarterService headquarterService;
    private DirectorRepository directorRepository;
    private DirectorService directorService;
    private AdministrativeService administrativeService;
    private SanitaryService sanitaryService;
    private MaterialAidService materialAidService;
    private HumanitarianAidService humanAidService;
    private HeadReportDTOConvert headReportDTOConvert;

    public List<PartnerDTO> findPartners() throws ContentNullException {
        return partnerService.findAll();
    }

    public List<HeadquarterReportDTO> findHeadquarterReports() throws ContentNullException {
        List<HeadquarterDTO> result = headquarterService.findAll();
        List<HeadquarterReportDTO> resultDTO = new ArrayList<>();
        for (HeadquarterDTO headquarter : result) {
            HeadquarterReportDTO dto = headReportDTOConvert.toDTO(headquarter);
            Optional<Director> director = directorRepository.findByCodeHq(dto.getCodeHq());
            director.ifPresent(value -> dto.setDirector(value.getFullName()));
            resultDTO.add(dto);
        }
        return resultDTO;
    }

    public List<SanitaryDTO> findSanitaryReports() throws ContentNullException {
        return sanitaryService.findAll();
    }

    public List<AdministrativeDTO> findAdministrativeReports() throws ContentNullException {
        return administrativeService.findAll();
    }

    public List<MaterialAidDTO> findMaterialAidReports() throws ContentNullException {
        return materialAidService.findAll();
    }

    public List<HumanitarianAidDTO> findHumanitarianAidReports() throws ContentNullException {
        return humanAidService.findAll();
    }
}
