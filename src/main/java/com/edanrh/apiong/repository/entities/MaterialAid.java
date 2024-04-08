package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @OneToMany
    @JoinColumn(name = "material_aid_id")
    private List<Material> material;

    @NotEmpty(message = "Material can't be empty")
    @Column(name = "id_headquarter", nullable = false)
    @ManyToOne
    private Headquarter headquarter;

    @NotEmpty(message = "Shipment can't be empty")
    @Column(name = "id_shipment", nullable = false)
    @ManyToOne
    private Shipment shipment;
    
}
