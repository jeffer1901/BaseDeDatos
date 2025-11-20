package com.example.basededatos.sancion;

import com.example.basededatos.jugador.Jugador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name="sanciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public enum TipoSancion {
        AMARILLA,
        ROJA,
        EXPULSION,
        MULTA
    }

    private TipoSancion tipo;
    private String observaciones;
    private Integer duracion;
    private LocalDate fecha;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;
}
