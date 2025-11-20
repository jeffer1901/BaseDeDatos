package com.example.basededatos.sancion;

import com.example.basededatos.jugador.JugadorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sanciones")
public class SancionController {

    private final SancionService sancionService;
    private final JugadorService jugadorService;

    public SancionController(SancionService sancionService, JugadorService jugadorService) {
        this.sancionService = sancionService;
        this.jugadorService = jugadorService;
    }

    // LISTAR
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("sanciones", sancionService.findAll());
        return "sancion/lista";
    }

    // FORM CREAR
    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("sancion", new Sancion());
        model.addAttribute("jugadores", jugadorService.findAll());
        model.addAttribute("tipos", Sancion.TipoSancion.values());
        return "sancion/crear";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Sancion sancion) {
        sancionService.save(sancion);
        return "redirect:/sancion";
    }

    // FORM EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Sancion sancion = sancionService.findById(id)
                .orElseThrow(() -> new RuntimeException("Sanción no encontrada"));

        model.addAttribute("sancion", sancion);
        model.addAttribute("jugadores", jugadorService.findAll());
        model.addAttribute("tipos", Sancion.TipoSancion.values());

        return "sancion/editar";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        sancionService.delete(id);
        return "redirect:/sancion";
    }

}