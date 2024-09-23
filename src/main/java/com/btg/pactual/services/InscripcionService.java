package com.btg.pactual.services;

import com.btg.pactual.domain.models.Inscripcion;
import com.btg.pactual.repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    private TransaccionService transaccionService;

    public Inscripcion suscribirCliente(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public void desuscribirCliente(String inscripcionId) {
        inscripcionRepository.deleteById(inscripcionId);
    }

    public List<Inscripcion> obtenerInscripcionesPorCliente(String clienteId) {
        return inscripcionRepository.findByClientId(clienteId);
    }

    public List<Inscripcion> obtenerInscripcionesActivas(String clienteId) {
        return inscripcionRepository.findActivasPorCliente(clienteId);
    }

    public void cancelarInscripcion(String inscripcionId) {
        Optional<Inscripcion> inscripcionOptional = inscripcionRepository.findById(inscripcionId);

        if (inscripcionOptional.isEmpty()) {
            throw new RuntimeException("Inscripcion no encontrada con el ID: " + inscripcionId);
        }

        Inscripcion inscripcion = inscripcionOptional.get();
        inscripcionRepository.delete(inscripcion);
        transaccionService.registrarTransaccion(inscripcion.getCliente(), inscripcion.getProducto(), "SALIDA", 0.0);
    }
}
