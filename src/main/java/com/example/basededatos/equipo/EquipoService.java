package com.example.basededatos.equipo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {
    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    public Optional<Equipo> findById(Long id) {
        return equipoRepository.findById(id);
    }

    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public void delete(Long id) {
        equipoRepository.deleteById(id);
    }
    public Equipo update(Long id, Equipo datosNuevos) {
        return equipoRepository.findById(id).map(equipo -> {

            equipo.setNombre(datosNuevos.getNombre());
            equipo.setCategoria(datosNuevos.getCategoria());
            equipo.setEntrenador(datosNuevos.getEntrenador());

            return equipoRepository.save(equipo);
        }).orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + id));
    }
    public List<Object[]> equiposConCantidad(){
        return equipoRepository.countJugadoresPorEquipo();
    }

}
