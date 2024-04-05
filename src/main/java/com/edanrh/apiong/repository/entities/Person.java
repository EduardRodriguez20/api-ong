package com.edanrh.apiong.repository.entities;

import com.edanrh.apiong.resources.enums.DocumentType;
import com.edanrh.apiong.resources.enums.Gender;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
// @Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "documentType")
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "document")
    private Long documentNumber;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "email")
    private String email;

    public String getFullName(){
        return this.name + " " + this.surname;
    }
}
