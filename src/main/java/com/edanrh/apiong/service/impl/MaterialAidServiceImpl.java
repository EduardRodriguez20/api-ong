package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.common.ErrorCode;
import com.edanrh.apiong.dto.MaterialAidDTO;
import com.edanrh.apiong.dto.MaterialDTO;
import com.edanrh.apiong.dto.converts.MaterialAidDTOConvert;
import com.edanrh.apiong.dto.converts.MaterialDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.HeadquarterRepository;
import com.edanrh.apiong.repository.MaterialAidRepository;
import com.edanrh.apiong.repository.entities.Headquarter;
import com.edanrh.apiong.repository.entities.Material;
import com.edanrh.apiong.repository.entities.MaterialAid;
import com.edanrh.apiong.repository.entities.Shipment;
import com.edanrh.apiong.service.MaterialAidService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MaterialAidServiceImpl implements MaterialAidService {

    private MaterialAidRepository materialAidRepository;
    private HeadquarterRepository headquarterRepository;
    private MaterialAidDTOConvert dtoConvert;
    private MaterialDTOConvert materialDTOConvert;

    @Override
    public List<MaterialAidDTO> findAll() throws ContentNullException {
        List<MaterialAid> result = (List<MaterialAid>) materialAidRepository.findAll();
        if (result.isEmpty()) {
            throw new ContentNullException(ErrorCode.MATERIAL_AID_CONTENT_NOT_FOUND, "There isn't material aid data", HttpStatus.NO_CONTENT);
        }else {
            List<MaterialAidDTO> resultDTO = new ArrayList<>();
            for (MaterialAid materialAid : result) {
                MaterialAidDTO dto = dtoConvert.toDTO(materialAid);
                for (Material material : materialAid.getMaterials()){
                    dto.getMaterial().add(materialDTOConvert.toDTO(material));
                }
                resultDTO.add(dtoConvert.toDTO(materialAid));
            }
            return resultDTO;
        }
    }

    @Override
    public MaterialAidDTO save(MaterialAidDTO materialAidDTO, Shipment shipment) throws NotFoundException {
        Optional<Headquarter> head = headquarterRepository.findByCodeHq(materialAidDTO.getCodeHq());
        if (head.isEmpty()){
            throw new NotFoundException(ErrorCode.HEADQUARTER_CODE_NOT_FOUND, "Headquarter not found", HttpStatus.NOT_FOUND);
        }else {
            MaterialAid entity = new MaterialAid();
            entity.setHeadquarter(head.get());
            entity.setShipment(shipment);
            for (MaterialDTO dto : materialAidDTO.getMaterial()){
                Material material = materialDTOConvert.toEntity(dto);
                entity.getMaterials().add(material);
            }
            return dtoConvert.toDTO(materialAidRepository.save(entity));
        }
    }
}
