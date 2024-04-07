package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name can't be empty")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotEmpty(message = "Code Shelter can't be empty")
    @Column(name = "code_sh", nullable = false)
    private String codePr;
    
    public void setCodeSh(){
        this.codePr = "PR-" + this.id;
    }
}
