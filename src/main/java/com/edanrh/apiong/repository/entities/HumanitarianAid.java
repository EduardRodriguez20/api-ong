package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
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

    @Column(name = "id_profession")
    @ManyToOne
    private Profession profession;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "id_headquarter")
    @ManyToOne
    private Headquarter headquarter;

    @Column(name = "id_shipment")
    @ManyToOne
    private Shipment shipment;
}
