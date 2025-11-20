package com.example.basededatos.partido;

import com.example.basededatos.competencia.Competencia;
import com.example.basededatos.equipo.Equipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="partidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partido {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private LocalTime hora;


    @ManyToOne
    @JoinColumn(name = "local_id")
    private Equipo equipoLocal;

    @ManyToOne
    @JoinColumn(name = "visitante_id")
    private Equipo equipoVisitante;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "competencia_id")
    private Competencia competencia;
}
