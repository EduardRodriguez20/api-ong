package com.edanrh.apiong.repository.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Email address can't be empty")
    private String email;

    @Column(name = "pwd")
    @NotEmpty(message = "Password can't be empty")
    private  String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private List<RoleEntity> roles = new ArrayList<>();

    public UserEntity (String email, String password){
        this.email = email;
        this.password = password;
    }
}
