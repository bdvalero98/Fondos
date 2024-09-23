package com.btg.pactual.application.services;

import com.btg.pactual.domain.models.Visitan;
import com.btg.pactual.domain.repositories.VisitanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitanService {

    @Autowired
    private VisitanRepository visitanRepository;

    public Visitan registrarVisita(Visitan visita) {
        return visitanRepository.save(visita);
    }

    public List<Visitan> obtenerVisitasPorSucursal(String sucursalId) {
        return visitanRepository.findBySucursalId(sucursalId);
    }

    public List<Visitan> obtenerVisitasPorCliente(String clienteId) {
        return visitanRepository.findByClienteId(clienteId);
    }
}
