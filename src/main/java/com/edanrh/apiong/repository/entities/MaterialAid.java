package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "materialAid")
    private Set<Material> material = new HashSet<>();

    @NotEmpty(message = "Headquarter can't be empty")
    @ManyToOne
    @JoinColumn(name = "id_headquarter", nullable = false)
    private Headquarter headquarter;

    @NotEmpty(message = "Shipment can't be empty")
    @ManyToOne
    @JoinColumn(name = "id_shipment", nullable = false)
    private Shipment shipment;
}
