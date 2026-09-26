package com.hacermonea.reservas.controller;

import com.hacermonea.reservas.model.Cliente;
import com.hacermonea.reservas.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Cliente guardar(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    public Cliente buscar(@PathVariable Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @GetMapping("/buscar")
    public List<Cliente> buscarPorNombre(@RequestParam String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }
}