package com.edanrh.apiong.repository.entities;

import java.time.LocalDateTime;
import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shipment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "code_shp", unique = true)
    private String codeShp;

    @NotEmpty(message = "Departure date can't be null")
    @Future(message = "The departure date can't be before the current one")
    @Column(name = "departure_date", nullable = false)
    private LocalDateTime departureDate;

    @NotNull(message = "Id shelter can't be null")
    @ManyToOne
    @JoinColumn(name = "id_shelter", nullable = false)
    private Shelter shelter;

    public void generateCodeShp(){
        this.codeShp = "SHP-" + this.id;
    }
    
}
