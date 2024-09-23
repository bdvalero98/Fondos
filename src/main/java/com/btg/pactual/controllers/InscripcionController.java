package com.btg.pactual.controllers;

import com.btg.pactual.services.InscripcionService;
import com.btg.pactual.domain.models.Inscripcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping
    public ResponseEntity<Inscripcion> suscribirCliente(@RequestBody Inscripcion inscripcion) {
        Inscripcion nuevaInscripcion = inscripcionService.suscribirCliente(inscripcion);
        return ResponseEntity.ok(nuevaInscripcion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desuscribirCliente(@PathVariable String id) {
        inscripcionService.desuscribirCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Inscripcion>> obtenerInscripcionesPorCliente(@PathVariable String clienteId) {
        List<Inscripcion> inscripciones = inscripcionService.obtenerInscripcionesPorCliente(clienteId);
        return ResponseEntity.ok(inscripciones);
    }
}
