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

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Partido partido,
                          @RequestParam Long equipoLocalId,
                          @RequestParam Long equipoVisitanteId,
                          @RequestParam Long competenciaId) {

        var local = equipoService.findById(equipoLocalId)
                .orElseThrow(() -> new IllegalArgumentException("Equipo local no encontrado"));

        var visitante = equipoService.findById(equipoVisitanteId)
                .orElseThrow(() -> new IllegalArgumentException("Equipo visitante no encontrado"));

        var competencia = competenciaService.findById(competenciaId)
                .orElseThrow(() -> new IllegalArgumentException("Competencia no encontrada"));

        partido.setEquipoLocal(local);
        partido.setEquipoVisitante(visitante);
        partido.setCompetencia(competencia);

        partidoService.save(partido);
        return "redirect:/partidos";
    }
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Partido partido,
                             @RequestParam Long equipoLocalId,
                             @RequestParam Long equipoVisitanteId,
                             @RequestParam Long competenciaId) {

        // 1️⃣ Buscar el partido original en la BD
        Partido partidoBD = partidoService.findById(partido.getId())
                .orElseThrow(() -> new IllegalArgumentException("Partido no encontrado"));

        // 2️⃣ Actualizar los campos enviados
        partidoBD.setFecha(partido.getFecha());
        partidoBD.setHora(partido.getHora());

        var local = equipoService.findById(equipoLocalId)
                .orElseThrow(() -> new IllegalArgumentException("Equipo local no encontrado"));
        var visitante = equipoService.findById(equipoVisitanteId)
                .orElseThrow(() -> new IllegalArgumentException("Equipo visitante no encontrado"));
        var competencia = competenciaService.findById(competenciaId)
                .orElseThrow(() -> new IllegalArgumentException("Competencia no encontrada"));

        partidoBD.setEquipoLocal(local);
        partidoBD.setEquipoVisitante(visitante);
        partidoBD.setCompetencia(competencia);

        // 3️⃣ Guardar todo actualizado
        partidoService.save(partidoBD);

        return "redirect:/partidos";
    }

    // FORMULARIO EDITAR
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {

        var partido = partidoService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Partido no encontrado"));

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
