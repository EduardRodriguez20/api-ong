package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Headquarter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "code_hq", unique = true)
    private String codeHq;

    @NotEmpty(message = "Name can't be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "Address can't be empty")
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull(message = "City can't be null")
    @OneToOne
    @JoinColumn(name = "id_city", unique = true, nullable = false)
    private City city;

    public void generateCodeHq(){
        this.codeHq = "HQ-" + this.city.getId() + "-" + this.id;
    }
}
