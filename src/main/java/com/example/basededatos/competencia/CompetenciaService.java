package com.example.basededatos.competencia;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenciaService {
    private final CompetenciaRepository competenciaRepository;

    public CompetenciaService(CompetenciaRepository competenciaRepository) {
        this.competenciaRepository = competenciaRepository;
    }

    public List<Competencia> findAll() { return competenciaRepository.findAll(); }

    public Optional<Competencia> findById(Long id) { return competenciaRepository.findById(id); }

    public Competencia save(Competencia competencia) { return competenciaRepository.save(competencia); }

    public void delete(Long id) { competenciaRepository.deleteById(id); }
}
