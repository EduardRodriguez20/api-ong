package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.dto.DirectorDTO;
import com.edanrh.apiong.dto.converts.DirectorDTOConvert;
import com.edanrh.apiong.repository.DirectorRepository;
import com.edanrh.apiong.repository.entities.Director;
import com.edanrh.apiong.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private DirectorRepository directorRepository;
    private DirectorDTOConvert dtoConvert;

    @Override
    public List<DirectorDTO> findAll() {
        List<Director> directors = (List<Director>) directorRepository.findAll();
        List<DirectorDTO> resultDto = new ArrayList<>();
        for (Director director : directors) {
            resultDto.add(dtoConvert.toDTO(director));
        }
        return resultDto;
    }

    @Override
    public DirectorDTO findByDocument(Long document) {
        Optional<Director> director = directorRepository.findByDocument(document);
        return director.map(entity -> dtoConvert.toDTO(entity)).orElse(null);
    }

    @Override
    public DirectorDTO save(DirectorDTO directorDTO) {
        Director director = directorRepository.save(dtoConvert.toEntity(directorDTO));
        return dtoConvert.toDTO(director);
    }

    @Override
    public boolean edit(Long document, DirectorDTO directorDTO) {
        Optional<Director> director = directorRepository.findByDocument(document);
        if (director.isPresent()) {
            Director save = dtoConvert.toEntity(directorDTO);
            save.setId(director.get().getId());
            directorRepository.save(save);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long document) {
        Optional<Director> director = directorRepository.findByDocument(document);
        if (director.isPresent()) {
            directorRepository.delete(director.get());
            return true;
        }
        return false;
    }
}
