package com.hacermonea.reservas.controller;

import com.hacermonea.reservas.model.Cliente;
import com.hacermonea.reservas.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Cliente cliente) {
        if (cliente.getEmail() != null && clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Ya existe un cliente con ese email"));
        }
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @GetMapping("/{id}")
    public Cliente buscar(@PathVariable Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @GetMapping("/buscar")
    public List<Cliente> buscarPorNombre(@RequestParam String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Cliente no encontrado"));
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("mensaje", "Cliente eliminado"));
    }
}