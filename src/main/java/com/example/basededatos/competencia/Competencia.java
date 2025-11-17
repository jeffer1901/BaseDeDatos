package com.example.basededatos.competencia;

import com.example.basededatos.partido.Partido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Competencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competencia {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipo;
    private double valorInscripcion;

    @OneToMany(mappedBy = "competencia")
    private List<Partido> partidos;
}
