package com.edanrh.apiong.dto.converts;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.edanrh.apiong.dto.PersonDTO;
import com.edanrh.apiong.repository.entities.Person;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PersonDTOConvert {

    private ModelMapper mapper;

    public PersonDTO toDTO(Person person) {
        return mapper.map(person, PersonDTO.class);
    }

    public Person toEntity(PersonDTO personDTO) {
        return mapper.map(personDTO, Person.class);
    }
    
}
