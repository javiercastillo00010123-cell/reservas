package com.hacermonea.reservas.repository;

import com.hacermonea.reservas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Busca citas entre dos momentos (ej: inicio y fin de un día)
    List<Cita> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}