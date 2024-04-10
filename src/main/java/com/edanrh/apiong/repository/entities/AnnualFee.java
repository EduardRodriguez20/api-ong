package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "annual_fee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnualFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "The fee's name can't be null")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotNull(message = "The amount can't be null")
    @Column(name = "amount", nullable = false)
    private double amount;

}
