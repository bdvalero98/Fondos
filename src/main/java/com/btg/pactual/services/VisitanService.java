package com.btg.pactual.services;

import com.btg.pactual.domain.exceptions.ClienteNotFoundException;
import com.btg.pactual.domain.exceptions.SucursalNotFoundException;
import com.btg.pactual.domain.exceptions.VisitanNotFoundException;
import com.btg.pactual.domain.models.Cliente;
import com.btg.pactual.domain.models.Sucursal;
import com.btg.pactual.domain.models.Visitan;
import com.btg.pactual.repositories.ClienteRepository;
import com.btg.pactual.repositories.SucursalRepository;
import com.btg.pactual.repositories.VisitanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class VisitanService {

    @Autowired
    private VisitanRepository visitanRepository;

    @Autowired
    private ClienteRepository ClienteRepository;

    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Visitan> obtenerTodasLasVisitas() {
        return visitanRepository.findAll();
    }

    public Visitan obtenerVisitaPorId(String idVisita) throws VisitanNotFoundException {
        return visitanRepository.findById(idVisita)
                .orElseThrow(() -> new VisitanNotFoundException("Visita con ID " + idVisita + " no encontrada."));
    }

    public Visitan registrarVisita(Visitan visitan) throws ClienteNotFoundException, SucursalNotFoundException {
        Cliente cliente = clienteRepository.findById(visitan.getCliente().getIdCliente())
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " +
                        visitan.getCliente().getIdCliente() + " no encontrado."));

        Sucursal sucursal = sucursalRepository.findById(visitan.getSucursal().getIdSucursal())
                .orElseThrow(() -> new SucursalNotFoundException("Sucursal con ID " +
                        visitan.getSucursal().getIdSucursal() + " no encontrado."));

        visitan.setIdVisitan(UUID.randomUUID().toString());
        visitan.setFechaVisita(LocalDate.now());

        return visitanRepository.save(visitan);
    }

    public void eliminarVisita(String idVisita) throws VisitanNotFoundException {
        Visitan visitan = obtenerVisitaPorId(idVisita);
        visitanRepository.delete(visitan);
    }
}
