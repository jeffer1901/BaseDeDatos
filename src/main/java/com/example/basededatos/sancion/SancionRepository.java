package com.example.basededatos.sancion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SancionRepository extends JpaRepository<Sancion,Long> {
    @Query("SELECT s FROM Sancion s WHERE s.jugador.id = :idJugador")
    List<Sancion> findByJugador(@Param("idJugador") Long idJugador);

    @Query("""
       SELECT s.jugador.nombre, COUNT(s)
       FROM Sancion s
       GROUP BY s.jugador.id, s.jugador.nombre
       ORDER BY COUNT(s) DESC
       """)
    List<Object[]> rankingJugadores();

    @Query("""
       SELECT s.jugador.equipo.nombre, COUNT(s)
       FROM Sancion s
       GROUP BY s.jugador.equipo.id, s.jugador.equipo.nombre
       ORDER BY COUNT(s) DESC
       """)
    List<Object[]> sancionesPorEquipo();
}
