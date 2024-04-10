package com.edanrh.apiong.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.edanrh.apiong.dto.UserDTO;
import com.edanrh.apiong.dto.converts.UserDTOConvert;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;
import com.edanrh.apiong.repository.RepositoryUser;
import com.edanrh.apiong.repository.entities.RoleEntity;
import com.edanrh.apiong.repository.entities.UserEntity;
import com.edanrh.apiong.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private RepositoryUser repositoryUser;
    private UserDTOConvert dtoConvert;

    @Override
    @Transactional
    public UserDTO save(UserDTO user) throws DuplicateCreationException, BussinesRuleException {
        Optional<UserEntity> existing = repositoryUser.findByEmail(user.getUsername());
        if (existing.isPresent()) {
            throw new DuplicateCreationException("code", "User already exists", HttpStatus.CONFLICT);
        }else{
            if (user.getRoleNames().contains("ROLE_DIRECTOR")){
                throw new BussinesRuleException("code", "You can't register a director role", HttpStatus.CONFLICT);
            }else if (!user.getRoleNames().contains("ROLE_ASSISTANT") && !user.getRoleNames().contains("ROLE_ADMIN")){
                throw new BussinesRuleException("code", "Incorrect roles, verify", HttpStatus.CONFLICT);
            }else {
                UserEntity entity = dtoConvert.toEntity(user);
                for (String rol : user.getRoleNames()){
                    entity.getRoles().add(new RoleEntity(rol));
                }
                return dtoConvert.toDTO(repositoryUser.save(entity));
            }
        }
    }

    @Override
    public UserDTO findByEmail(String email) throws NotFoundException {
        Optional<UserEntity> existing = repositoryUser.findByEmail(email);
        if (existing.isPresent()) {
            return dtoConvert.toDTO(existing.get());
        }else{
            throw new NotFoundException("code", "User not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<UserDTO> findAll() throws ContentNullException {
        List<UserEntity> users = (List<UserEntity>) repositoryUser.findAll();
        if (users.isEmpty()){
            throw new ContentNullException("code", "There isn't users", HttpStatus.NO_CONTENT);
        }else {
            List<UserDTO> resultDto = new ArrayList<>();
            for (UserEntity user : users) {
                resultDto.add(dtoConvert.toDTO(user));
            }
            return resultDto;
        }
    }

    @Override
    @Transactional
    public boolean edit(String email, UserDTO user) throws NotFoundException, BussinesRuleException {
        Optional<UserEntity> existing = repositoryUser.findByEmail(email);
        if (existing.isEmpty()){
            throw new NotFoundException("code", "User not found", HttpStatus.NOT_FOUND);
        }else{
            if (user.getRoleNames().contains("ROLE_DIRECTOR")){
                throw new BussinesRuleException("code", "You can't register a director role", HttpStatus.CONFLICT);
            }else {
                UserEntity entity = existing.get();
                entity.setPassword(user.getPassword());
                repositoryUser.save(entity);
                return true;
            }
        }
    }

    @Override
    @Transactional
    public boolean deleteByEmail(String email) throws NotFoundException {
        Optional<UserEntity> existing = repositoryUser.deleteByEmail(email);
        if (existing.isEmpty()){
            throw new NotFoundException("code", "User not found", HttpStatus.NOT_FOUND);
        }else {
            repositoryUser.delete(existing.get());
            return true;
        }
    }
}
