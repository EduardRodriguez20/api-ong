package com.edanrh.apiong.repository.entities;

import com.edanrh.apiong.resources.enums.DocumentType;
import com.edanrh.apiong.resources.enums.Gender;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name can't be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "Surname can't be empty")
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotEmpty(message = "Document type can't be empty")
    @Column(name = "documentType", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @NotEmpty(message = "Document number can't be empty")
    @Column(name = "document", nullable = false)
    private Long documentNumber;

    @NotEmpty(message = "Gender can't be empty")
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Email(message = "Must be a valid email address")
    @Column(name = "email", nullable = false)
    private String email;

    public String getFullName(){
        return this.name + " " + this.surname;
    }
}
