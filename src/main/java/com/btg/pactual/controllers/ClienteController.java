package com.btg.pactual.controllers;

import com.btg.pactual.application.services.ClienteService;
import com.btg.pactual.domain.exceptions.ResourceNotFoundException;
import com.btg.pactual.domain.models.Cliente;
import com.btg.pactual.domain.models.Inscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return ResponseEntity.ok(nuevoCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);

        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + id);
        }

        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}/inscripciones")
    public ResponseEntity<List<Inscripcion>> obtenerHistorialInscripciones(@PathVariable String id) {
        List<Inscripcion> inscripciones = clienteService.obtenerHistorialInscripciones(id);
        return ResponseEntity.ok(inscripciones);
    }
}
