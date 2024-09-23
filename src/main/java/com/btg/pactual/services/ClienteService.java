package com.btg.pactual.services;

import com.btg.pactual.domain.models.Cliente;
import com.btg.pactual.domain.models.Inscripcion;
import com.btg.pactual.repositories.ClienteRepository;
import com.btg.pactual.repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obtenerClientePorId(String idCliente) {
        return clienteRepository.findById(idCliente).orElse(null);
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    public List<Inscripcion> obtenerHistorialInscripciones(String clienteId) {
        return inscripcionRepository.findByClienteId(clienteId);
    }
}
