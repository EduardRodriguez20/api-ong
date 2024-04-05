package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administratives")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Administrative extends Person{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "The profession can't be empty")
    @Column(name = "id_profession", nullable = false)
    @ManyToOne
    private Profession profession;

    @NotEmpty(message = "The headquarter can't be empty")
    @Column(name = "id_headquarter", nullable = false)
    @ManyToOne
    private Headquarter headquarter;
}
