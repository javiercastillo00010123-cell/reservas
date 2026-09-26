package com.hacermonea.reservas.controller;

import com.hacermonea.reservas.model.Cita;
import com.hacermonea.reservas.repository.CitaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaRepository citaRepository;

    public CitaController(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    // Listar todas las citas
    @GetMapping
    public List<Cita> listar() {
        return citaRepository.findAll();
    }

    // Guardar una cita nueva
    @PostMapping
    public Cita guardar(@RequestBody Cita cita) {
        return citaRepository.save(cita);
    }

    // Buscar una cita por id
    @GetMapping("/{id}")
    public Cita buscar(@PathVariable Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    // Confirmar una cita: PUT /api/citas/1/confirmar
    @PutMapping("/{id}/confirmar")
    public Cita confirmar(@PathVariable Long id) {
        Cita cita = citaRepository.findById(id).orElse(null);
        if (cita != null) {
            cita.setEstado("CONFIRMADA");
            return citaRepository.save(cita);
        }
        return null;
    }

    // Cancelar una cita: PUT /api/citas/1/cancelar
    @PutMapping("/{id}/cancelar")
    public Cita cancelar(@PathVariable Long id) {
        Cita cita = citaRepository.findById(id).orElse(null);
        if (cita != null) {
            cita.setEstado("CANCELADA");
            return citaRepository.save(cita);
        }
        return null;
    }

    // Ver citas de un día: /api/citas/dia?fecha=2026-10-01
    @GetMapping("/dia")
    public List<Cita> citasDelDia(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();          // ese día a las 00:00
        LocalDateTime fin = fecha.atTime(23, 59, 59);         // ese día a las 23:59
        return citaRepository.findByFechaHoraBetween(inicio, fin);
    }
}