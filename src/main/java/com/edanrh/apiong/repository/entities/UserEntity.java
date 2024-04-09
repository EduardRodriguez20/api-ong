package com.edanrh.apiong.repository.entities;

import java.math.BigInteger;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotEmpty(message = "Email address can't be empty")
    private String email;

    @Column(name = "pwd")
    @NotEmpty(message = "Password can't be empty")
    private  String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private List<RoleEntity> roles;
}
