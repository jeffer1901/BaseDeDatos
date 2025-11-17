package com.example.basededatos.jugador;

import com.example.basededatos.equipo.Equipo;
import com.example.basededatos.sancion.Sancion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="jugadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jugador {
    @Id
    private long id;
    private String nombre;
    private String apellido;
    private String posicion;
    private int numero;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    @OneToMany(mappedBy = "jugador")
    private List<Sancion> sanciones;
}
