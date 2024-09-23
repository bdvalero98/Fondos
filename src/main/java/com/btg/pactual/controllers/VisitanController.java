package com.btg.pactual.controllers;

import com.btg.pactual.application.services.VisitanService;
import com.btg.pactual.domain.models.Visitan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitas")
public class VisitanController {

    @Autowired
    private VisitanService visitanService;

    @PostMapping
    public ResponseEntity<Visitan> registrarVisita(@RequestBody Visitan visita) {
        Visitan nuevaVisita = visitanService.registrarVisita(visita);
        return ResponseEntity.ok(nuevaVisita);
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<Visitan>> obtenerVisitasPorSucursal(@PathVariable String sucursalId) {
        List<Visitan> visitas = visitanService.obtenerVisitasPorSucursal(sucursalId);
        return ResponseEntity.ok(visitas);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Visitan>> obtenerVisitasPorCliente(@PathVariable String clienteId) {
        List<Visitan> visitas = visitanService.obtenerVisitasPorCliente(clienteId);
        return ResponseEntity.ok(visitas);
    }
}
