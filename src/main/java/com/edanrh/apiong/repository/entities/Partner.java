package com.edanrh.apiong.repository.entities;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "The account can't be null")
    @Column(name = "account")
    private Long bankAccount;

    @NotEmpty(message = "The id fee can't be null")
    @Column(name = "id_fee")
    @ManyToOne
    private AnnualFee fee;

    @NotEmpty(message = "The payment date can't be null")
    @Column(name = "payment_date")
    private Date paymentDate;
}
