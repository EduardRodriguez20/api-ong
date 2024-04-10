package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.common.ErrorCode;
import com.edanrh.apiong.dto.HumanitarianAidDTO;
import com.edanrh.apiong.dto.MaterialAidDTO;
import com.edanrh.apiong.dto.ShipmentDTO;
import com.edanrh.apiong.dto.converts.HumanitarianAidDTOConvert;
import com.edanrh.apiong.dto.converts.MaterialAidDTOConvert;
import com.edanrh.apiong.dto.converts.ShipmentDTOConvert;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.exceptions.QuantityEntityException;
import com.edanrh.apiong.repository.HumanitarianAidRepository;
import com.edanrh.apiong.repository.MaterialAidRepository;
import com.edanrh.apiong.repository.ShelterRepository;
import com.edanrh.apiong.repository.ShipmentRepository;
import com.edanrh.apiong.repository.entities.HumanitarianAid;
import com.edanrh.apiong.repository.entities.MaterialAid;
import com.edanrh.apiong.repository.entities.Shelter;
import com.edanrh.apiong.repository.entities.Shipment;
import com.edanrh.apiong.service.HumanitarianAidService;
import com.edanrh.apiong.service.MaterialAidService;
import com.edanrh.apiong.service.ShipmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private ShipmentRepository shipmentRepository;
    private HumanitarianAidRepository humanitarianAidRepository;
    private MaterialAidRepository materialAidRepository;
    private ShelterRepository shelterRepository;
    private ShipmentDTOConvert dtoConvert;
    private HumanitarianAidDTOConvert humanAidDTOConvert;
    private MaterialAidDTOConvert materialAidDTOConvert;
    private MaterialAidService materialAidService;
    private HumanitarianAidService humanitarianAidService;

    @Override
    public List<ShipmentDTO> findAll() throws ContentNullException {
        List<Shipment> result = (List<Shipment>) shipmentRepository.findAll();
        if (result.isEmpty()){
            throw new ContentNullException(ErrorCode.SHIPMENT_CONTENT_NOT_FOUND, "Headquarter not found, codeHq invalid", HttpStatus.NOT_FOUND);
        }else {
            List<ShipmentDTO> resultDTOs = new ArrayList<>();
            for (Shipment shipment : result) {
                ShipmentDTO dto = dtoConvert.toDTO(shipment);
                dto.setCodeSh(shipment.getShelter().getCodeSh());
                List<HumanitarianAid> humanitarianAids = humanitarianAidRepository.findByCodeShp(dto.getCodeShp());
                List<MaterialAid> materialAids = materialAidRepository.findByCodeShp(dto.getCodeShp());
                if (!humanitarianAids.isEmpty()){
                    for (HumanitarianAid humanitarianAid : humanitarianAids) {
                        dto.getHumanAid().add(humanAidDTOConvert.toDTO(humanitarianAid));
                    }
                }
                if (!materialAids.isEmpty()){
                    for (MaterialAid materialAid : materialAids) {
                        dto.getMaterialAid().add(materialAidDTOConvert.toDTO(materialAid));
                    }
                }
                resultDTOs.add(dto);
            }
            return resultDTOs;
        }
    }

    @Override
    public ShipmentDTO save(ShipmentDTO shipmentDTO) throws
            NotFoundException, ContentNullException, QuantityEntityException, BussinesRuleException {
        Optional<Shelter> shelter = shelterRepository.findByCodeSh(shipmentDTO.getCodeSh());
        LocalDateTime now = LocalDateTime.now();
        if (shelter.isEmpty()){
            throw new NotFoundException(ErrorCode.SHELTER_CODE_NOT_FOUND, "Shelter not found, codeSh invalid", HttpStatus.NOT_FOUND);
        } else if (shipmentDTO.getDepartureDate().isBefore(now)) {
            throw new BussinesRuleException(ErrorCode.DATE_VALIDATION, "Departure date can't be before now", HttpStatus.BAD_REQUEST);
        } else {
            Shipment shipment = dtoConvert.toEntity(shipmentDTO);
            shipment.setShelter(shelter.get());
            Shipment saved = shipmentRepository.save(shipment);
            shipment.setId(saved.getId());
            shipment.generateCodeShp();
            ShipmentDTO dto = dtoConvert.toDTO(shipment);
            dto.setCodeSh(shipment.getShelter().getCodeSh());
            if (!shipmentDTO.getMaterialAid().isEmpty()){
                for (MaterialAidDTO materialDTO : shipmentDTO.getMaterialAid()){
                    dto.getMaterialAid().add(materialAidService.save(materialDTO, shipment));
                }
            }
            if (!shipmentDTO.getHumanAid().isEmpty()) {
                for (HumanitarianAidDTO humanDTO : shipmentDTO.getHumanAid()) {
                    dto.getHumanAid().add(humanitarianAidService.save(humanDTO, shipment));
                }
            }
            shipmentRepository.save(shipment);
            return dto;
        }
    }

    @Override
    public boolean edit(String codeShp, ShipmentDTO shipmentDTO) throws NotFoundException {
        Optional<Shipment> existing = shipmentRepository.findByCodeShp(codeShp);
        if (existing.isEmpty()){
            throw new NotFoundException(ErrorCode.SHIPMENT_CODE_NOT_FOUND, "Shipment not found, codeShp invalid", HttpStatus.NOT_FOUND);
        }else {
            Optional<Shelter> shelter = shelterRepository.findByCodeSh(shipmentDTO.getCodeSh());
            if (shelter.isEmpty()){
                throw new NotFoundException(ErrorCode.SHELTER_CODE_NOT_FOUND, "Shelter not found, codeSh invalid", HttpStatus.NOT_FOUND);
            }else {
                Shipment shipment = existing.get();
                shipment.setShelter(shelter.get());
                shipment.setDepartureDate(shipmentDTO.getDepartureDate());
                shipmentRepository.save(shipment);
                return true;
            }
        }

    }

}
