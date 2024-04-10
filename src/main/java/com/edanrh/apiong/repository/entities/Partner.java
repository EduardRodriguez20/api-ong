package com.edanrh.apiong.repository.entities;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "The headquarter can't be empty")
    @ManyToOne
    @JoinColumn(name = "id_headquarter", nullable = false)
    private Headquarter headquarter;

    @NotNull(message = "The account can't be null")
    @Column(name = "account", nullable = false)
    private Long bankAccount;

    @NotNull(message = "The id fee can't be null")
    @ManyToOne
    @JoinColumn(name = "id_fee", nullable = false)
    private AnnualFee fee;

    @NotNull(message = "The payment date can't be null")
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    public void generatePaymentDate() {
        this.paymentDate = LocalDate.now();
    }
}
