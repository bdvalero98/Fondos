package com.btg.pactual.controllers;

import com.btg.pactual.domain.exceptions.ClienteNotFoundException;
import com.btg.pactual.domain.exceptions.TransaccionNotFoundException;
import com.btg.pactual.domain.models.Transaccion;
import com.btg.pactual.services.TransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
@Tag(name = "Transacciones", description = "Operaciones relacionadas con las transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @Operation(summary = "Obtener todas las transacciones")
    @GetMapping
    public ResponseEntity<List<Transaccion>> obtenerTodasLasTransacciones() {
        return new ResponseEntity<>(transaccionService.obtenerTodasLasTransacciones(), HttpStatus.OK);
    }

    @Operation(summary = "Obtener transaccion por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> obtenerTransaccionPorId(@PathVariable String id) {
        try {
            return new ResponseEntity<>(transaccionService.obtenerTransaccionPorId(id), HttpStatus.OK);
        } catch (TransaccionNotFoundException transaccionNotFoundException) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Crear una nueva Transaccion")
    @PostMapping
    public ResponseEntity<Transaccion> crearTransaccion(@RequestBody Transaccion transaccion) {
        try {
            return new ResponseEntity<>(transaccionService.crearTransaccion(transaccion), HttpStatus.CREATED);
        } catch (ClienteNotFoundException clienteNotFoundException) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Actualizar transaccion por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Transaccion> actualizarTransaccion(@PathVariable String id,
                                                             @RequestBody Transaccion transaccionActualizada) {
        try {
            return new ResponseEntity<>(transaccionService.actualizarTransaccion(id,
                    transaccionActualizada), HttpStatus.OK);
        } catch (TransaccionNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar transaccion por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Transaccion> eliminarTransaccion(@PathVariable String id) {
        try {
            transaccionService.eliminarTransaccion(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TransaccionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
