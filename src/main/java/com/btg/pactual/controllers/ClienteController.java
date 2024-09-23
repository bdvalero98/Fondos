package com.btg.pactual.controllers;

import com.btg.pactual.domain.usecases.GestionInscripcionesUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final GestionInscripcionesUseCase gestionInscripcionesUseCase;

    public ClienteController(GestionInscripcionesUseCase gestionInscripcionesUseCase) {
        this.gestionInscripcionesUseCase = gestionInscripcionesUseCase;
    }

    @PostMapping("/{clienteId}/suscribir/{productoId}")
    public String suscribirCliente(@PathVariable String clienteId, @PathVariable String productoId) {
        gestionInscripcionesUseCase.suscribirClienteAProducto(clienteId, productoId);
        return "Cliente suscrito exitosamente";
    }

}
