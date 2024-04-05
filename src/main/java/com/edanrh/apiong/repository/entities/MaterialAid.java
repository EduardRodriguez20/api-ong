package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "material_aid")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialAid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_material")
    @OneToOne
    private Material material;

    @Column(name = "id_headquarter")
    @ManyToOne
    private Headquarter headquarter;

    @Column(name = "id_shipment")
    @ManyToOne
    private Shipment shipment;
    
}
