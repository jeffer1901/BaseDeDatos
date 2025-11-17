package com.example.basededatos.equipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipoRepository extends JpaRepository<Equipo,Long> {
    @Query("SELECT e.nombre, COUNT(j) " +
            "FROM Jugador j JOIN j.equipo e " +
            "GROUP BY e.id, e.nombre")
    List<Object[]> countJugadoresPorEquipo();
}
