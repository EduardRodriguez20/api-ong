package com.edanrh.apiong.service.impl;

import com.edanrh.apiong.common.ValidateEmail;
import com.edanrh.apiong.dto.DirectorDTO;
import com.edanrh.apiong.dto.converts.DirectorDTOConvert;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.DirectorRepository;
import com.edanrh.apiong.repository.HeadquarterRepository;
import com.edanrh.apiong.repository.PersonRepository;
import com.edanrh.apiong.repository.RepositoryUser;
import com.edanrh.apiong.repository.entities.*;
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
    private PersonRepository personRepository;
    private RepositoryUser repositoryUser;
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
            throw new NotFoundException("code", "Document invalid, don't exist", HttpStatus.NOT_FOUND);
        }else {
            return dtoConvert.toDTO(director.get());
        }
    }

    @Override
    public DirectorDTO save(DirectorDTO directorDTO) throws NotFoundException, DuplicateCreationException, BussinesRuleException {
        Optional<Headquarter> headquarter = headquarterRepository.findByCodeHq(directorDTO.getCodeHq());
        Optional<Director> existing = directorRepository.findByDocument(directorDTO.getData().getDocumentNumber());
        Optional<Person> person = personRepository.findByEmail(directorDTO.getData().getEmail());
        if (existing.isPresent()){
            throw new DuplicateCreationException("code", "Director already exists", HttpStatus.CONFLICT);
        }else if (headquarter.isEmpty()){
            throw new NotFoundException("code", "CodeHq invalid, don't exists", HttpStatus.NOT_FOUND);
        } else if (person.isPresent()) {
            throw new DuplicateCreationException("code", "Email isn't available, already exists", HttpStatus.CONFLICT);
        } else if (!ValidateEmail.validateEmail(directorDTO.getData().getEmail())) {
            throw new BussinesRuleException("code", "Must be a valid email address", HttpStatus.CONFLICT);
        } else {
            Director director = dtoConvert.toEntity(directorDTO);
            director.setHeadquarter(headquarter.get());
            UserEntity user = new UserEntity(directorDTO.getData().getEmail(), directorDTO.getPassword());
            user.getRoles().add(new RoleEntity("DIRECTOR"));
            repositoryUser.save(user);
            return dtoConvert.toDTO(directorRepository.save(director));
        }
    }

    @Override
    public boolean edit(Long document, DirectorDTO directorDTO) throws NotFoundException, DuplicateCreationException, BussinesRuleException {
        Optional<Director> director = directorRepository.findByDocument(document);
        Optional<Headquarter> headquarter = headquarterRepository.findByCodeHq(directorDTO.getCodeHq());
        Optional<Person> person = personRepository.findByEmail(directorDTO.getData().getEmail());
        if (headquarter.isEmpty()){
            throw new NotFoundException("code", "CodeHq invalid, don't exists", HttpStatus.NOT_FOUND);
        }else if (director.isEmpty()){
            throw new NotFoundException("code", "Document invalid, don't exist", HttpStatus.NOT_FOUND);
        } else if (person.isPresent()) {
            throw new DuplicateCreationException("code", "Email isn't available", HttpStatus.CONFLICT);
        } else if (!ValidateEmail.validateEmail(directorDTO.getData().getEmail())) {
            throw new BussinesRuleException("code", "Must be a valid email address", HttpStatus.CONFLICT);
        } else {
            Optional<UserEntity> user = repositoryUser.findByEmail(directorDTO.getData().getEmail());
            if (user.isEmpty()) {
                throw new NotFoundException("code", "Error editing director credentials", HttpStatus.INTERNAL_SERVER_ERROR);
            }else {
                user.get().setEmail(directorDTO.getData().getEmail());
                user.get().setPassword(directorDTO.getPassword());
                repositoryUser.save(user.get());
            }
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
            Optional<UserEntity> user = repositoryUser.findByEmail(director.get().getEmail());
            if (user.isEmpty()) {
                throw new NotFoundException("code", "Error deleting director credentials", HttpStatus.INTERNAL_SERVER_ERROR);
            }else {
                repositoryUser.delete(user.get());
            }
            directorRepository.delete(director.get());
            return true;
        }else {
            throw new NotFoundException("code", "Document invalid, don't exist", HttpStatus.NOT_FOUND);
        }
    }
}
