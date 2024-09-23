package com.btg.pactual.services;

import com.btg.pactual.domain.models.Cliente;
import com.btg.pactual.domain.models.Producto;
import com.btg.pactual.domain.models.Transaccion;
import com.btg.pactual.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private ClienteService clienteService;

    public Transaccion registrarTransaccion(Cliente cliente, String tipoTransaccion, Double monto) {
        Transaccion transaccion = new Transaccion();
        transaccion.setCliente(cliente);
        transaccion.setTipoTransaccion(tipoTransaccion);
        transaccion.setMonto(monto);
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setIdTransaccion(UUID.randomUUID().toString());
        return transaccionRepository.save(transaccion);
    }

    public List<Transaccion> obtenerHistorialPorCliente(String clienteId) {
        Cliente cliente = clienteService.obtenerClientePorId(clienteId);
        return transaccionRepository.findByCliente(cliente);
    }

    public List<Transaccion> obtenerTodo() {
        return transaccionRepository.findAll();
    }

    public List<Transaccion> ObtenerHistorialTransacciones(Cliente cliente) {
        return transaccionRepository.findByClienteOrderByFechaDesc(cliente);
    }

}
