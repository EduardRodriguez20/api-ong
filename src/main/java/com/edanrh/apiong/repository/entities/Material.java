package com.edanrh.apiong.repository.entities;

import com.edanrh.apiong.resources.enums.MaterialType;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotEmpty(message = "The material can't be empty")
    @Column(name = "material_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MaterialType material;

    @NotNull(message = "Quantity can't be empty")
    @Column(name = "quantity", nullable = false)
    private double quantity;

    @NotEmpty(message = "Description can't be empty")
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "material_aid_id")
    private MaterialAid materialAid;
}
