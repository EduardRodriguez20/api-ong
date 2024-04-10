package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrative")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Administrative extends Person{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "The profession can't be null")
    @ManyToOne
    @JoinColumn(name = "id_profession", nullable = false)
    private Profession profession;

    @NotNull(message = "The headquarter can't be null")
    @ManyToOne
    @JoinColumn(name = "id_headquarter", nullable = false)
    private Headquarter headquarter;
}
