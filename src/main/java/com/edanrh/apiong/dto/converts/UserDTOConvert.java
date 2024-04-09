package com.edanrh.apiong.dto.converts;

import com.edanrh.apiong.dto.UserDTO;
import com.edanrh.apiong.repository.entities.RoleEntity;
import com.edanrh.apiong.repository.entities.UserEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDTOConvert {

    private ModelMapper modelMapper;

    public UserDTO toDTO(UserEntity user) {
        UserDTO dto = modelMapper.map(user, UserDTO.class);
        for (RoleEntity roles : user.getRoles()){
            dto.getRoles().add(roles.getName());
        }
        return modelMapper.map(user, UserDTO.class);
    }

    public UserEntity toEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

}
