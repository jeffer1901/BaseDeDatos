package com.example.basededatos.competencia;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/competencias")
public class CompetenciaController {

    private final CompetenciaService competenciaService;

    public CompetenciaController(CompetenciaService competenciaService) {
        this.competenciaService = competenciaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("competencias", competenciaService.findAll());
        return "competencias/lista";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("competencia", new Competencia());
        return "competencias/crear";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Competencia competencia) {
        competenciaService.save(competencia);
        return "redirect:/competencias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("competencia", competenciaService.findById(id));
        return "competencias/editar";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Competencia competencia) {
        competenciaService.save(competencia);
        return "redirect:/competencias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        competenciaService.delete(id);
        return "redirect:/competencias";
    }
}
