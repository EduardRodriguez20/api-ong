package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.common.ErrorCode;
import com.edanrh.apiong.dto.ProfessionDTO;
import com.edanrh.apiong.dto.converts.ProfessionDTOConvert;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.ProfessionRepository;
import com.edanrh.apiong.repository.entities.Profession;
import com.edanrh.apiong.service.ProfessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfessionServiceImpl implements ProfessionService {

    private ProfessionRepository professionRepository;
    private ProfessionDTOConvert dtoConvert;

    @Override
    public List<ProfessionDTO> findAll() throws ContentNullException {
        List<Profession> result = (List<Profession>) professionRepository.findAll();
        if (result.isEmpty()) {
            throw new ContentNullException(ErrorCode.PROFESSION_CONTENT_NOT_FOUND, "There aren't profession data", HttpStatus.NO_CONTENT);
        }else {
            List<ProfessionDTO> resultDTO = new ArrayList<>();
            for (Profession profession : result) {
                resultDTO.add(dtoConvert.toDTO(profession));
            }
            return resultDTO;
        }
    }

    @Override
    public ProfessionDTO save(ProfessionDTO professionDTO) throws DuplicateCreationException {
        Optional<Profession> existing = professionRepository.findByName(professionDTO.getName());
        if (existing.isPresent()) {
            throw new DuplicateCreationException(ErrorCode.PROFESSION_DUPLICATE_CREATION, "Profession already exists", HttpStatus.CONFLICT);
        }else {
            Profession saved = professionRepository.save(dtoConvert.toEntity(professionDTO));
            saved.generateCodeSh();
            professionRepository.save(saved);
            return dtoConvert.toDTO(saved);
        }
    }

    @Override
    public boolean edit(String codePr, ProfessionDTO professionDTO) throws NotFoundException {
        Optional<Profession> existing = professionRepository.findByCodePr(codePr);
        if (existing.isEmpty()) {
            throw new NotFoundException(ErrorCode.PROFESSION_CODE_NOT_FOUND, "Profession doesn't exist, valid codePr", HttpStatus.NOT_FOUND);
        }else {
            existing.get().setName(professionDTO.getName());
            professionRepository.save(existing.get());
            return true;
        }
    }

    @Override
    public boolean delete(String codePr) throws NotFoundException {
        Optional<Profession> existing = professionRepository.findByCodePr(codePr);
        if (existing.isEmpty()) {
            throw new NotFoundException(ErrorCode.PROFESSION_CODE_NOT_FOUND, "Profession doesn't exist, valid codePr", HttpStatus.NOT_FOUND);
        }else {
            professionRepository.delete(existing.get());
            return true;
        }
    }
}
