package com.example.basededatos.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="usuarios")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long id;

    private String username;
    private String password;
    private String rol;
}
