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
public class Shelter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name can't be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "City can't be empty")
    @ManyToOne
    @JoinColumn(name = "id_city", nullable = false)
    private City city;

    @NotEmpty(message = "Address can't be empty")
    @Column(name = "address", unique = true)
    private String address;

    @Column(name = "code_sh", unique = true)
    private String codeSh;

    public void generateCodeSh(){
        this.codeSh = "SH-" + this.city.getId() + "-" + this.id;
    }
}
