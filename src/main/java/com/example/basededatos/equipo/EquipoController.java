package com.example.basededatos.equipo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    // LISTAR
    @GetMapping
    public String listarEquipos(Model model) {
        model.addAttribute("equipos", equipoService.findAll());
        return "equipos/lista";
    }

    // FORMULARIO CREAR
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "equipos/form";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardarEquipo(@ModelAttribute Equipo equipo) {
        equipoService.save(equipo);
        return "redirect:/equipos";
    }

    // FORMULARIO EDITAR
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Equipo equipo = equipoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        model.addAttribute("equipo", equipo);
        return "equipos/form";
    }

    // ACTUALIZAR
    @PostMapping("/actualizar/{id}")
    public String actualizarEquipo(@PathVariable Long id, @ModelAttribute Equipo equipo) {
        equipoService.update(id, equipo);
        return "redirect:/equipos";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarEquipo(@PathVariable Long id) {
        equipoService.delete(id);
        return "redirect:/equipos";
    }

}