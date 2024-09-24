package com.btg.pactual.controllers;

import com.btg.pactual.domain.exceptions.ClienteNotFoundException;
import com.btg.pactual.domain.exceptions.InscripcionNotFoundException;
import com.btg.pactual.domain.models.Inscripcion;
import com.btg.pactual.services.InscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@Tag(name = "Inscripcion", description = "Operaciones relacionadas con las inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Operation(summary = "Obtener todas las inscripciones")
    @GetMapping
    public ResponseEntity<List<Inscripcion>> obtenerTodasLasInscripciones() {
        return new ResponseEntity<>(inscripcionService.obtenerTodasLasInscripciones(), HttpStatus.OK);
    }

    @Operation(summary = "Obtener inscripcion por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerInscripcionPorId(@PathVariable String id) {
        try {
            return new ResponseEntity<>(inscripcionService.obtenerInscripcionPorId(id), HttpStatus.OK);
        } catch (InscripcionNotFoundException inscripcionNotFoundException) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Crear una nueva inscripcion")
    @PostMapping
    public ResponseEntity<Inscripcion> crearInscripcion(@RequestBody Inscripcion inscripcion) {
        return new ResponseEntity<>(inscripcionService.crearInscripcion(inscripcion), HttpStatus.OK);
    }

    @Operation(summary = "Actualizar inscripcion por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Inscripcion> actualizarInscripcion(@PathVariable String id,
                                                             @RequestBody Inscripcion inscripcionActualizada) {
        try {
            return new ResponseEntity<>(inscripcionService.actualizarInscripcion(id, inscripcionActualizada), HttpStatus.OK);
        } catch (InscripcionNotFoundException inscripcionNotFoundException) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar inscripcion por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable String id) {
        try {
            inscripcionService.eliminarInscripcion(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InscripcionNotFoundException inscripcionNotFoundException) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Cancelar inscripcion de un cliente")
    @DeleteMapping("/cancelar/{idCliente}/{idInscripcion}")
    public ResponseEntity<Void> cancelarInscripcionDeCliente(@PathVariable String idCliente,
                                                             @PathVariable String idInscripcion) {
        try {
            inscripcionService.cancelarInscripcionDeCliente(idCliente, idInscripcion);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InscripcionNotFoundException | ClienteNotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
