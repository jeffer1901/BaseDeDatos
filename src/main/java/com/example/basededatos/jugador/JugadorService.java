package com.example.basededatos.jugador;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {
    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }

    public Optional<Jugador> findById(Long id) {
        return jugadorRepository.findById(id);
    }

    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public void delete(Long id) {
        jugadorRepository.deleteById(id);
    }
    public List<Jugador> jugadoresPorEquipo(Long idEquipo){
        return jugadorRepository.findByEquipo(idEquipo);
    }

}
