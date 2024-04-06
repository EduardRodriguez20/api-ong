package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Material can't be empty")
    @Column(name = "id_material", nullable = false)
    @OneToOne
    private Material material;

    @NotEmpty(message = "Material can't be empty")
    @Column(name = "id_headquarter", nullable = false)
    @ManyToOne
    private Headquarter headquarter;

    @NotEmpty(message = "Shipment can't be empty")
    @Column(name = "id_shipment", nullable = false)
    @ManyToOne
    private Shipment shipment;
    
}
