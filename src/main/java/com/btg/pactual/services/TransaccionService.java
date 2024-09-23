package com.btg.pactual.services;

import com.btg.pactual.domain.models.Cliente;
import com.btg.pactual.domain.models.Transaccion;
import com.btg.pactual.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private ClienteService clienteService;

    public Transaccion registrarTransaccion(String clienteId, String tipoTransaccion, Double monto) {
        Cliente cliente = clienteService.obtenerClientePorId(clienteId);

        if (cliente == null) {
            throw new IllegalArgumentException("El cliente con Id: " + clienteId + " no existe.");
        }

        Transaccion nuevaTransaccion = new Transaccion();
        nuevaTransaccion.setCliente(cliente);
        nuevaTransaccion.setTipoTransaccion(tipoTransaccion);
        nuevaTransaccion.setMonto(monto);
        nuevaTransaccion.setFecha(LocalDateTime.now());

        return transaccionRepository.save(nuevaTransaccion);
    }

    public List<Transaccion> obtenerHistorialPorCliente(String clienteId) {
        Cliente cliente = clienteService.obtenerClientePorId(clienteId);
        return transaccionRepository.findByCliente(cliente);
    }

    public List<Transaccion> obtenerTodo() {
        return transaccionRepository.findAll();
    }

}
