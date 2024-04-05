package com.edanrh.apiong.repository.entities;

import com.edanrh.apiong.resources.enums.MaterialType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "material")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "material_type")
    @Enumerated(EnumType.STRING)
    private MaterialType material;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "description")
    private String description;
}
