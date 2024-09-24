package com.btg.pactual.services;

import com.btg.pactual.domain.exceptions.ClienteNotFoundException;
import com.btg.pactual.domain.models.*;
import com.btg.pactual.repositories.ClienteRepository;
import com.btg.pactual.repositories.InscripcionRepository;
import com.btg.pactual.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private DisponibilidadService disponibilidadService;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public Cliente crearCliente(Cliente cliente) {
        cliente.setSaldo(500000.0);
        return clienteRepository.save(cliente);
    }

    public Cliente obtenerClientePorId(String idCliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);

        if (clienteOptional.isPresent()) {
            return clienteOptional.get();
        } else {
            throw new ClienteNotFoundException("Cliente con ID " + idCliente + " no encontrado.");
        }
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente actualizarCliente(String idCliente, Cliente clienteActualizado) throws ClienteNotFoundException {
        Cliente clienteExistente = obtenerClientePorId(idCliente);

        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setApellido(clienteActualizado.getApellido());
        clienteExistente.setTelefono(clienteActualizado.getTelefono());
        clienteExistente.setEmail(clienteActualizado.getEmail());
        clienteExistente.setCiudad(clienteActualizado.getCiudad());
        clienteExistente.setSaldo(clienteActualizado.getSaldo());
        clienteExistente.setInscripciones(clienteActualizado.getInscripciones());
        clienteExistente.setVisitas(clienteActualizado.getVisitas());
        clienteExistente.setTransacciones(clienteActualizado.getTransacciones());

        return clienteRepository.save(clienteExistente);
    }

    public void eliminarCliente(String idCliente) {
        Cliente clienteExistente = obtenerClientePorId(idCliente);
        clienteRepository.delete(clienteExistente);
    }

    public boolean clienteExiste(String idCliente) {
        return clienteRepository.existsById(idCliente);
    }

    public List<Cliente> obtenerClientesConInscripciones() {
        return clienteRepository.findAll();
    }

    public List<Cliente> obtenerClientesConTransacciones() {
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

    public void cancelarInscripcion(String idCliente, String idInscripcion) throws Exception {
        Cliente cliente = obtenerClientePorId(idCliente);

        boolean inscripcionExiste = cliente.getInscripciones().
                removeIf(inscripcion -> inscripcion.getIdInscripcion().equals(idInscripcion));

        if (!inscripcionExiste) {
            throw new Exception("Inscripcion con ID " + idInscripcion + " no encontrada en el cliente.");
        }

        clienteRepository.save(cliente);
    }

    public Inscripcion suscribirClienteAProducto(Cliente cliente, Producto producto, Sucursal sucursal) {
        if (disponibilidadService.productoDisponible(producto, sucursal)) {
            return suscribirAFondo(cliente, producto);
        }
        throw new RuntimeException("Producto no disponible en la sucursal seleccionada");
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
