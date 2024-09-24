package com.btg.pactual.controllers;

import com.btg.pactual.domain.exceptions.ClienteNotFoundException;
import com.btg.pactual.domain.exceptions.SucursalNotFoundException;
import com.btg.pactual.domain.exceptions.VisitanNotFoundException;
import com.btg.pactual.domain.models.Visitan;
import com.btg.pactual.services.VisitanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitas")
@Tag(name = "Visitas", description = "Operaciones relacionadas con las visitas de clientes")
public class VisitanController {

    @Autowired
    private VisitanService visitanService;

    @Operation(summary = "Obtener todas las visitas")
    @GetMapping
    public ResponseEntity<List<Visitan>> obtenerTodasLasVisitas() {
        return new ResponseEntity<>(visitanService.obtenerTodasLasVisitas(), HttpStatus.OK);
    }

    @Operation(summary = "Obtener una visita por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Visitan> obtenerVisitaPorId(@PathVariable String id) {
        try {
            return new ResponseEntity<>(visitanService.obtenerVisitaPorId(id), HttpStatus.OK);
        } catch (VisitanNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Registrar una nueva visita")
    @PostMapping
    public ResponseEntity<Visitan> registrarVisita(@RequestBody Visitan visitan) {
        try {
            return new ResponseEntity<>(visitanService.registrarVisita(visitan), HttpStatus.CREATED);
        } catch (ClienteNotFoundException | SucursalNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar una visita por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVisita(@PathVariable String id) {
        try {
            visitanService.eliminarVisita(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VisitanNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
