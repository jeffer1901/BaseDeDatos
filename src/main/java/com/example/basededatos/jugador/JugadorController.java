package com.example.basededatos.jugador;

import com.example.basededatos.equipo.EquipoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;
    private final EquipoService equipoService;

    public JugadorController(JugadorService jugadorService, EquipoService equipoService) {
        this.jugadorService = jugadorService;
        this.equipoService = equipoService;
    }

    // LISTAR
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("jugadores", jugadorService.findAll());
        return "jugadores/lista";
    }

    // FORMULARIO CREAR
    @GetMapping("/crear")
    public String crearForm(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("equipos", equipoService.findAll());
        return "jugadores/crear";
    }

    // GUARDAR NUEVO
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("jugador") Jugador jugador,
                          @RequestParam Long equipoId) {

        var equipo = equipoService.findById(equipoId)
                .orElseThrow(() -> new IllegalArgumentException("Equipo no encontrado"));

        jugador.setEquipo(equipo);

        jugadorService.save(jugador);

        return "redirect:/jugadores";
    }
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("jugador") Jugador jugador,
                             @RequestParam Long equipoId) {

        var equipo = equipoService.findById(equipoId)
                .orElseThrow(() -> new IllegalArgumentException("Equipo no encontrado"));

        jugador.setEquipo(equipo);

        jugadorService.save(jugador);

        return "redirect:/jugadores";
    }
    // FORMULARIO EDITAR
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        var jugador = jugadorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID no válido: " + id));

        model.addAttribute("jugador", jugador);
        model.addAttribute("equipos", equipoService.findAll());
        return "jugadores/editar";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        jugadorService.delete(id);
        return "redirect:/jugadores";
    }

}
