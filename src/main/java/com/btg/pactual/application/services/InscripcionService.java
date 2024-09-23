package com.btg.pactual.application.services;

import com.btg.pactual.domain.models.Inscripcion;
import com.btg.pactual.domain.repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public Inscripcion suscribirCliente(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public void desuscribirLiente(String inscripcionId) {
        inscripcionRepository.deleteById(inscripcionId);
    }

    public List<Inscripcion> obtenerInscripcionesPorCliente(String clienteId) {
        return inscripcionRepository.findByClienteId(clienteId);
    }

    public List<Inscripcion> obtenerInscripcionesActivas(String clienteId) {
        return inscripcionRepository.findActivasPorCliente(clienteId);
    }
}
