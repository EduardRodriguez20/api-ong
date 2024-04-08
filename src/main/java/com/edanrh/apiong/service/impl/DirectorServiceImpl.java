package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.dto.DirectorDTO;
import com.edanrh.apiong.dto.converts.DirectorDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.DirectorRepository;
import com.edanrh.apiong.repository.HeadquarterRepository;
import com.edanrh.apiong.repository.entities.Director;
import com.edanrh.apiong.repository.entities.Headquarter;
import com.edanrh.apiong.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private DirectorRepository directorRepository;
    private HeadquarterRepository headquarterRepository;
    private DirectorDTOConvert dtoConvert;

    @Override
    public List<DirectorDTO> findAll() throws ContentNullException {
        List<Director> directors = (List<Director>) directorRepository.findAll();
        if(directors.isEmpty()){
            throw new ContentNullException("code", "There isn't directors", HttpStatus.NO_CONTENT);
        }else {
            List<DirectorDTO> resultDto = new ArrayList<>();
            for (Director director : directors) {
                resultDto.add(dtoConvert.toDTO(director));
            }
            return resultDto;
        }
    }

    @Override
    public DirectorDTO findByDocument(Long document) throws NotFoundException {
        Optional<Director> director = directorRepository.findByDocument(document);
        if (director.isEmpty()){
            throw new NotFoundException("code", "Document invalid, don't exist", HttpStatus.NO_CONTENT);
        }else {
            return dtoConvert.toDTO(director.get());
        }
    }

    @Override
    public DirectorDTO save(DirectorDTO directorDTO) throws NotFoundException, DuplicateCreationException {
        Optional<Headquarter> headquarter = headquarterRepository.findByCodeHq(directorDTO.getCodeHq());
        Optional<Director> existing = directorRepository.findByDocument(directorDTO.getData().getDocumentNumber());
        if (existing.isPresent()){
            throw new DuplicateCreationException("code", "Director already exists", HttpStatus.CONFLICT);
        }else if (headquarter.isEmpty()){
            throw new NotFoundException("code", "CodeHq invalid, don't exists", HttpStatus.NOT_FOUND);
        }else {
            Director director = dtoConvert.toEntity(directorDTO);
            director.setHeadquarter(headquarter.get());
            return dtoConvert.toDTO(directorRepository.save(director));
        }
    }

    @Override
    public boolean edit(Long document, DirectorDTO directorDTO) throws NotFoundException {
        Optional<Director> director = directorRepository.findByDocument(document);
        Optional<Headquarter> headquarter = headquarterRepository.findByCodeHq(directorDTO.getCodeHq());
        if (headquarter.isEmpty()){
            throw new NotFoundException("code", "CodeHq invalid, don't exists", HttpStatus.NOT_FOUND);
        }else if (director.isEmpty()){
            throw new NotFoundException("code", "Document invalid, don't exist", HttpStatus.NOT_FOUND);
        }else {
            Director save = dtoConvert.toEntity(directorDTO);
            save.setId(director.get().getId());
            save.setHeadquarter(headquarter.get());
            directorRepository.save(save);
            return true;
        }
    }

    @Override
    public boolean delete(Long document) throws NotFoundException {
        Optional<Director> director = directorRepository.findByDocument(document);
        if (director.isPresent()) {
            directorRepository.delete(director.get());
            return true;
        }else {
            throw new NotFoundException("code", "Document invalid, don't exist", HttpStatus.NOT_FOUND);
        }
    }
}
