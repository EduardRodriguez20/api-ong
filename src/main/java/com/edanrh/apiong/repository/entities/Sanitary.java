package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sanitaries")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Sanitary extends Person{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Profession id can't be null")
    @Column(name = "id_profession", nullable = false)
    @ManyToOne
    private Profession profession;

    @NotEmpty(message = "Avaliable can't be null")
    @Column(name = "is_avaliable")
    private Boolean isAvaliable;
}
