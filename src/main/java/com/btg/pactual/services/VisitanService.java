package com.btg.pactual.services;

import com.btg.pactual.domain.models.Visitan;
import com.btg.pactual.repositories.VisitanRepository;
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
        return visitanRepository.findBySucursalPorId(sucursalId);
    }

    public List<Visitan> obtenerVisitasPorCliente(String clienteId) {
        return visitanRepository.findByClientePorId(clienteId);
    }
}
