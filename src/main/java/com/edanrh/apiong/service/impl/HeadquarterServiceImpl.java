package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.dto.HeadquarterDTO;
import com.edanrh.apiong.dto.converts.HeadquarterDTOConvert;
import com.edanrh.apiong.repository.HeadquarterRepository;
import com.edanrh.apiong.repository.entities.Headquarter;
import com.edanrh.apiong.service.HeadquarterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HeadquarterServiceImpl implements HeadquarterService {

    private HeadquarterRepository headquarterRepository;
    private HeadquarterDTOConvert dtoConvert;

    @Override
    public List<HeadquarterDTO> findAll() {
        List<Headquarter> result = (List<Headquarter>) headquarterRepository.findAll();
        List<HeadquarterDTO> resultDTO = new ArrayList<>();
        for (Headquarter headquarter : result) {
            resultDTO.add(dtoConvert.toDTO(headquarter));
        }
        return resultDTO;
    }

    @Override
    public HeadquarterDTO findByCodeHq(String codeHq) {
        Optional<Headquarter> headquarter = headquarterRepository.findByCodeHq(codeHq);
        return headquarter.map(value -> dtoConvert.toDTO(value)).orElse(null);
    }

    @Override
    public HeadquarterDTO save(HeadquarterDTO headquarterDTO) {
        Headquarter entity = headquarterRepository.save(dtoConvert.toEntity(headquarterDTO));
        return dtoConvert.toDTO(entity);
    }

    @Override
    public boolean edit(String codeHq, HeadquarterDTO headquarterDTO) {
        return false;
    }

    @Override
    public boolean deleteByCodeHq(String codeHq) {
        return false;
    }
}
