package com.example.basededatos.usuario;

import com.example.basededatos.equipo.EquipoService;
import com.example.basededatos.jugador.JugadorService;
import com.example.basededatos.partido.PartidoService;
import com.example.basededatos.sancion.SancionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reportes")
public class ReporteController {
    private final SancionService sancionService;
    private final JugadorService jugadorService;
    private final EquipoService equipoService;
    private final PartidoService partidoService;

    public ReporteController(SancionService sancionService, JugadorService jugadorService, EquipoService equipoService,PartidoService partidoService) {
        this.sancionService = sancionService;
        this.jugadorService = jugadorService;
        this.equipoService = equipoService;
        this.partidoService = partidoService;
    }

    @GetMapping("")
    public String menuReportes() {
        return "reportes/reportes";
    }

    @GetMapping("/intermedios")
    public String reportesIntermedios() {
        return "reportes/reportesIntermedios/reportes-intermedios";
    }

    @GetMapping("/complejos")
    public String reportesComplejos() {
        return "reportes/reportesComplejos/reportes-complejos";
    }
    @GetMapping("/reportesIntermedios/sanciones-por-jugador")
    public String formularioSanciones() {
        return "reportes/reportesIntermedios/sanciones-por-jugador";
    }

    // Procesar la consulta
    @GetMapping("/reportesIntermedios/sanciones-por-jugador/buscar")
    public String sancionesPorJugador(@RequestParam Long id, Model model){
        model.addAttribute("sanciones", sancionService.sancionesPorJugador(id));
        return "reportes/reportesIntermedios/sanciones-por-jugador";
    }
    @GetMapping("/reportesComplejos/ranking-jugadores")
    public String rankingJugadores(Model model){
        model.addAttribute("ranking", sancionService.rankingJugadores());
        return "reportes/reportesComplejos/ranking-jugadores";
    }
    @GetMapping("/reportesComplejos/sanciones-por-equipo")
    public String equiposSanciones(Model model){
        model.addAttribute("lista", sancionService.equiposConMasSanciones());
        return "reportes/reportesComplejos/sanciones-por-equipo";
    }
    @GetMapping("/reportesIntermedios/equipos-con-jugadores")
    public String equiposJugadores(Model model){
        model.addAttribute("lista", equipoService.equiposConCantidad());
        return "reportes/reportesIntermedios/equipos-con-jugadores";
    }
    @GetMapping("/reportesIntermedios/jugadores-por-equipo")
    public String jugadoresPorEquipo(@RequestParam(required = false) Long id, Model model){
        if (id != null) {
            model.addAttribute("jugadores", jugadorService.jugadoresPorEquipo(id));
        }
        return "reportes/reportesIntermedios/jugadores-por-equipo";
    }
    @GetMapping("/reportesIntermedios/partidos-por-competencia")
    public String partidosCompetencia(@RequestParam(required = false) Long id, Model model){
        if (id != null) {
            model.addAttribute("partidos", partidoService.partidosPorCompetencia(id));
        }

        return "reportes/reportesIntermedios/partidos-por-competencia";
    }
    @GetMapping("/reportesComplejos/historial")
    public String historial(
            @RequestParam(required = false) Long id1,
            @RequestParam(required = false) Long id2,
            Model model) {

        model.addAttribute("equipos", equipoService.findAll());

        if (id1 != null && id2 != null) {
            model.addAttribute("partidos", partidoService.historial(id1, id2));
        }

        return "reportes/reportesComplejos/historial";
    }
}