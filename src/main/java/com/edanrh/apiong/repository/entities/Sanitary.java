package com.edanrh.apiong.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sanitaries")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Sanitary extends Person{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Profession can't be null")
    @ManyToOne
    @JoinColumn(name = "id_profession", nullable = false)
    private Profession profession;

    @NotNull(message = "Headquarter can't be null")
    @ManyToOne
    @JoinColumn(name = "id_headquarter", nullable = false)
    private Headquarter headquarter;

    @NotNull(message = "Available can't be null")
    @Column(name = "is_available")
    private Boolean isAvailable;

    private int participation = 0;

    public void addParticipation(){
        this.participation++;
    }

    public void available(){
        this.isAvailable = true;
    }
}
