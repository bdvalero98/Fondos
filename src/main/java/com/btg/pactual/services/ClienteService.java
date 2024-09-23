package com.btg.pactual.services;

import com.btg.pactual.domain.models.*;
import com.btg.pactual.repositories.ClienteRepository;
import com.btg.pactual.repositories.InscripcionRepository;
import com.btg.pactual.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private DisponibilidadService disponibilidadService;

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public Cliente crearCliente(Cliente cliente) {
        cliente.setSaldo(500000.0);
        return clienteRepository.save(cliente);
    }

    public Cliente obtenerClientePorId(String idCliente) {
        return clienteRepository.findById(idCliente).orElse(null);
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    public List<Inscripcion> obtenerHistorialInscripciones(String clienteId) {
        return inscripcionRepository.findByClientId(clienteId);
    }

    public Inscripcion suscribirAFondo(Cliente cliente, Producto producto) {
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setCliente(cliente);
        inscripcion.setProducto(producto);
        return inscripcionRepository.save(inscripcion);
    }

    public void cancelarInscripcion(String idInscripcion) {
        inscripcionRepository.deleteById(idInscripcion);
    }

    public Inscripcion suscribirClienteAProducto(Cliente cliente, Producto producto, Sucursal sucursal) {
        if (disponibilidadService.productoDisponible(producto, sucursal)) {
            return suscribirAFondo(cliente, producto);
        }
        throw new RuntimeException("Producto no disponible en la sucursal seleccionada");
    }

    public void cancelarInscripcionYRegistrarTransaccion(String idInscripcion, Cliente cliente, Producto producto) {
        cancelarInscripcion(idInscripcion);
        registrarTransaccion(cliente, producto, "SALIDA");
    }

    private void registrarTransaccion(Cliente cliente, Producto producto, String tipoTransaccion) {
        Transaccion transaccion = new Transaccion();
        transaccion.setCliente(cliente);
        transaccion.setTipoTransaccion(tipoTransaccion);
        transaccion.setMonto(0.0);
        transaccion.setFecha(LocalDateTime.now());

        transaccionRepository.save(transaccion);
    }
}
