package com.edanrh.apiong.service;

import java.util.List;

import com.edanrh.apiong.dto.UserDTO;
import com.edanrh.apiong.exceptions.BussinesRuleException;
import com.edanrh.apiong.exceptions.ContentNullException;
import com.edanrh.apiong.exceptions.DuplicateCreationException;
import com.edanrh.apiong.exceptions.NotFoundException;

public interface UserService {
    UserDTO save(UserDTO user) throws DuplicateCreationException, BussinesRuleException;
    UserDTO findByEmail(String email) throws NotFoundException;
    List<UserDTO> findAll() throws ContentNullException;
    boolean edit(String email, UserDTO user) throws NotFoundException, BussinesRuleException;
    boolean deleteByEmail(String email) throws NotFoundException;
}

