package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.dto.AdministrativeDTO;
import com.edanrh.apiong.dto.converts.AdministrativeDTOConvert;
import com.edanrh.apiong.repository.AdministrativeRepository;
import com.edanrh.apiong.repository.entities.Administrative;
import com.edanrh.apiong.service.AdministrativeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdministrativeServiceImpl implements AdministrativeService {

    private AdministrativeRepository administrativeRepository;
    private AdministrativeDTOConvert dtoConvert;

    @Override
    public List<AdministrativeDTO> findAll() {
        List<Administrative> result = (List<Administrative>) administrativeRepository.findAll();
        List<AdministrativeDTO> resultDTO = new ArrayList<>();
        for (Administrative entity : result) {
            resultDTO.add(dtoConvert.toDTO(entity));
        }
        return resultDTO;
    }

    @Override
    public AdministrativeDTO findByDocument(Long document) {
        Optional<Administrative> result = administrativeRepository.findByDocument(document);
        return result.map(administrative -> dtoConvert.toDTO(administrative)).orElse(null);
    }

    @Override
    public AdministrativeDTO save(AdministrativeDTO administrativeDTO) {
        Administrative entity = administrativeRepository.save(dtoConvert.toEntity(administrativeDTO));
        return dtoConvert.toDTO(entity);
    }

    @Override
    public boolean edit(Long document, AdministrativeDTO administrativeDTO) {
        Optional<Administrative> result = administrativeRepository.findByDocument(document);
        if (result.isPresent()) {
            Administrative save = dtoConvert.toEntity(administrativeDTO);
            save.setId(result.get().getId());
            administrativeRepository.save(save);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long document) {
        Optional<Administrative> result = administrativeRepository.findByDocument(document);
        if (result.isPresent()) {
            administrativeRepository.delete(result.get());
            return true;
        }
        return false;
    }
}
