package com.example.basededatos.partido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartidoRepository extends JpaRepository<Partido,Long> {
    @Query("SELECT p FROM Partido p WHERE p.competencia.id = :idCompetencia")
    List<Partido> findByCompetencia(Long idCompetencia);

    @Query("""
       SELECT p
       FROM Partido p
       WHERE (p.equipoLocal.id = :id1 AND p.equipoVisitante.id = :id2)
          OR (p.equipoLocal.id = :id2 AND p.equipoVisitante.id = :id1)
       """)
    List<Partido> historial(@Param("id1") Long id1, @Param("id2") Long id2);
}
