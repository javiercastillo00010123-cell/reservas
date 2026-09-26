package com.hacermonea.reservas.repository;

import com.hacermonea.reservas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Busca clientes cuyo nombre contenga el texto (ignora mayúsculas)
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
}