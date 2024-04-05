package com.edanrh.apiong.repository.entities;

import java.util.Date;

import com.edanrh.apiong.resources.enums.FeeType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partners")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Partner extends Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account")
    private Long bankAccount;

    @Column(name = "fee_type")
    @Enumerated(EnumType.STRING)
    private FeeType feeType;

    @Column(name = "payment_date")
    private Date paymentDate;
}
