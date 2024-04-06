package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "humanitarian_aid")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HumanitarianAid {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Profession can't be null")
    @Column(name = "id_profession", nullable = false)
    @ManyToOne
    private Profession profession;

    @NotEmpty(message = "Quantity can't be null")
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @NotEmpty(message = "Headquarter can't be null")
    @Column(name = "id_headquarter", nullable = false)
    @ManyToOne
    private Headquarter headquarter;

    @NotEmpty(message = "Shipment can't be null")
    @Column(name = "id_shipment", nullable = false)
    @ManyToOne
    private Shipment shipment;
}
