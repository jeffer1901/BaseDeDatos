package com.example.basededatos.sancion;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SancionService {
    private final SancionRepository sancionRepository;

    public SancionService(SancionRepository sancionRepository) {
        this.sancionRepository = sancionRepository;
    }

    public List<Sancion> findAll() {
        return sancionRepository.findAll();
    }

    public Optional<Sancion> findById(Long id) {
        return sancionRepository.findById(id);
    }

    public Sancion save(Sancion sancion) {
        return sancionRepository.save(sancion);
    }

    public void delete(Long id) {
        sancionRepository.deleteById(id);
    }
    public List<Sancion> sancionesPorJugador(Long idJugador){
        return sancionRepository.findByJugador(idJugador);
    }
    public List<Object[]> rankingJugadores(){
        return sancionRepository.rankingJugadores();
    }
    public List<Object[]> equiposConMasSanciones(){
        return sancionRepository.sancionesPorEquipo();
    }
}
