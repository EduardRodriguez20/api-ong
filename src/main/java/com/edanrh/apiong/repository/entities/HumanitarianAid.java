package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "humanitarian_aid")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HumanitarianAid {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Profession can't be null")
    @ManyToOne
    @JoinColumn(name = "id_profession", nullable = false)
    private Profession profession;

    @NotEmpty(message = "Quantity can't be null")
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @NotNull(message = "Headquarter can't be null")
    @ManyToOne
    @JoinColumn(name = "id_headquarter", nullable = false)
    private Headquarter headquarter;

    @NotNull(message = "Shipment can't be null")
    @ManyToOne
    @JoinColumn(name = "id_shipment", nullable = false)
    private Shipment shipment;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "aid_sanitary_document",
            joinColumns = @JoinColumn(name = "humanitarian_aid_id"),
            inverseJoinColumns = @JoinColumn(name = "sanitary_sent_id")
    )
    private Set<DocSanitarySent> documentsSanitaries = new HashSet<>();
}
