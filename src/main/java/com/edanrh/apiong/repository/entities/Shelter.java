package com.edanrh.apiong.repository.entities;

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
public class Shelter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name can't be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "City can't be empty")
    @ManyToOne
    @JoinColumn(name = "id_city", nullable = false)
    private City city;

    @NotEmpty(message = "Address can't be empty")
    @Column(name = "address", unique = true)
    private String address;

    @NotEmpty(message = "Code Shelter can't be empty")
    @Column(name = "code_sh", unique = true, nullable = false)
    private String codeSh;

    public void generateCodeSh(){
        this.codeSh = "SH-" + this.city.getId() + "-" + this.id;
    }
}
