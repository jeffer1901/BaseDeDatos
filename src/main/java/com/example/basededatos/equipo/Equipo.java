package com.example.basededatos.equipo;

import com.example.basededatos.jugador.Jugador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name="equipos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String nombre;
    private String categoria;
    private String entrenador;

    @ToString.Exclude
    @OneToMany(mappedBy = "equipo")
    private List<Jugador> jugadores;
}
