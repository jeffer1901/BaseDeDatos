package com.example.basededatos.jugador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    @Query("SELECT j FROM Jugador j WHERE j.equipo.id = :idEquipo")
    List<Jugador> findByEquipo(Long idEquipo);
}
