package com.edanrh.apiong.repository.entities;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Departure date can't be null")
    @Column(name = "departure_date", nullable = false)
    private Date departureDate;

    @NotEmpty(message = "Id shelter can't be null")
    @Column(name = "id_shelter", nullable = false)
    @ManyToOne
    private Shelter shelter;
    
}
