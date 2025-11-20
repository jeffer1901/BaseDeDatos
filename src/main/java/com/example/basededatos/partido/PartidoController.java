package com.example.basededatos.partido;

import com.example.basededatos.competencia.CompetenciaService;
import com.example.basededatos.equipo.EquipoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/partidos")
public class PartidoController {

    private final PartidoService partidoService;
    private final EquipoService equipoService;
    private final CompetenciaService competenciaService;

    public PartidoController(PartidoService partidoService,
                             EquipoService equipoService,
                             CompetenciaService competenciaService) {

        this.partidoService = partidoService;
        this.equipoService = equipoService;
        this.competenciaService = competenciaService;
    }

    // LISTAR
    @GetMapping
    public String listarPartidos(Model model) {
        model.addAttribute("partidos", partidoService.findAll());
        return "partidos/lista";
    }

    // FORMULARIO CREAR
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("partido", new Partido());
        model.addAttribute("equipos", equipoService.findAll());
        model.addAttribute("competencias", competenciaService.findAll());
        return "partidos/crear";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardarPartido(@ModelAttribute Partido partido) {
        partidoService.save(partido);
        return "redirect:/partidos";
    }

    // FORMULARIO EDITAR
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Partido partido = partidoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        model.addAttribute("partido", partido);
        model.addAttribute("equipos", equipoService.findAll());
        model.addAttribute("competencias", competenciaService.findAll());

        return "partidos/editar";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminarPartido(@PathVariable Long id) {
        partidoService.delete(id);
        return "redirect:/partidos";
    }
    @GetMapping("reportes/reportesIntermedios/partidos-por-competencia/{id}")
    public String partidosCompetencia(@PathVariable Long id, Model model){
        model.addAttribute("partidos", partidoService.partidosPorCompetencia(id));
        return "reportes/partidos_competencia";
    }
    @GetMapping("/reportes/reportesComplejos/historial/{id1}/{id2}")
    public String historial(@PathVariable Long id1, @PathVariable Long id2, Model model){
        model.addAttribute("partidos", partidoService.historial(id1, id2));
        return "/reportes/reportesComplejos/historial";
    }
}
