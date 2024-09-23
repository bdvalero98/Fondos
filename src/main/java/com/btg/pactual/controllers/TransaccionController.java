package com.btg.pactual.controllers;

import com.btg.pactual.domain.models.Transaccion;
import com.btg.pactual.services.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaccion")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/registrar")
    public ResponseEntity<Transaccion> registrarTransaccion(@RequestParam String clienteId,
                                                            @RequestParam Double monto,
                                                            @RequestParam String tipo) {
        Transaccion transaccion = transaccionService.registrarTransaccion(clienteId, tipo, monto);
        return ResponseEntity.ok(transaccion);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Transaccion>> obtenerHistorialPorCliene(@PathVariable String clientedId) {
        List<Transaccion> transacciones = transaccionService.obtenerHistorialPorCliente(clientedId);
        return ResponseEntity.ok(transacciones);
    }

    @GetMapping
    public ResponseEntity<List<Transaccion>> obtenerTodasLasTransacciones() {
        List<Transaccion> transacciones = transaccionService.obtenerTodo();
        return ResponseEntity.ok(transacciones);
    }
}
