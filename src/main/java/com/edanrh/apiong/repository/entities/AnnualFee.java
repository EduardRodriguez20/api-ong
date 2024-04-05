package com.edanrh.apiong.repository.entities;

import com.edanrh.apiong.resources.enums.FeeType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnualFee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private FeeType type;

    @Column(name = "amount")
    private double amount;

}
