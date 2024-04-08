package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.dto.MaterialAidDTO;
import com.edanrh.apiong.dto.converts.MaterialAidDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.MaterialAidRepository;
import com.edanrh.apiong.repository.entities.MaterialAid;
import com.edanrh.apiong.repository.entities.Shipment;
import com.edanrh.apiong.service.MaterialAidService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MaterialAidServiceImpl implements MaterialAidService {

    private MaterialAidRepository materialAidRepository;
    private MaterialAidDTOConvert dtoConvert;

    @Override
    public List<MaterialAidDTO> findAll() throws ContentNullException {
        List<MaterialAid> result = (List<MaterialAid>) materialAidRepository.findAll();
        if (result.isEmpty()) {
            throw new ContentNullException("code", "There isn't material aid data", HttpStatus.NO_CONTENT);
        }else {
            List<MaterialAidDTO> resultDTO = new ArrayList<>();
            for (MaterialAid materialAid : result) {
                resultDTO.add(dtoConvert.toDTO(materialAid));
            }
            return resultDTO;
        }
    }

    @Override
    public MaterialAidDTO save(MaterialAidDTO materialAidDTO, Shipment shipment) {
        return null;
    }
}
