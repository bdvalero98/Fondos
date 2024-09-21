package com.btg.fondos.web.controllers;

import com.btg.fondos.application.services.FondoService;
import com.btg.fondos.domain.models.Fondo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fondos")
@RequiredArgsConstructor
public class FondoController {

    private final FondoService fondoService;

    @GetMapping
    public List<Fondo> obtenerFondos() {
        return fondoService.obtenerFondos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fondo> obtenerFondoPorId(@PathVariable String id) {
        return ResponseEntity.ok(fondoService.obtenerFondoPorId(id));
    }

    @PostMapping
    public ResponseEntity<Fondo> crearFondo(@RequestBody Fondo fondo) {
        return new ResponseEntity<>(fondoService.guardarFondo(fondo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFondo(@PathVariable String id) {
        fondoService.eliminarFondo(id);
        return ResponseEntity.noContent().build();
    }
}
