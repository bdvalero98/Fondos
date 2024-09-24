package com.btg.pactual.services;

import com.btg.pactual.domain.exceptions.ClienteNotFoundException;
import com.btg.pactual.domain.exceptions.TransaccionNotFoundException;
import com.btg.pactual.domain.models.Cliente;
import com.btg.pactual.domain.models.Transaccion;
import com.btg.pactual.repositories.ClienteRepository;
import com.btg.pactual.repositories.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Transaccion> obtenerTodasLasTransacciones() {
        return transaccionRepository.findAll();
    }

    public Transaccion obtenerTransaccionPorId(String idTransaccion) throws TransaccionNotFoundException {
        Optional<Transaccion> transaccionOptional = transaccionRepository.findById(idTransaccion);

        if (transaccionOptional.isPresent()) {
            return transaccionOptional.get();
        } else {
            throw new TransaccionNotFoundException("Transaccion con ID " + idTransaccion + " no encontrado.");
        }
    }

    public Transaccion crearTransaccion(Transaccion transaccion) throws Exception {
        Cliente cliente = clienteRepository.findById(transaccion.getCliente().getIdCliente())
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con ID "
                        + transaccion.getCliente().getIdCliente() + " no encontrado."));

        double saldoActual = cliente.getSaldo();

        if (saldoActual < transaccion.getMonto()) {
            throw new Exception("Saldo insuficiente para realizar la transaccion.");
        }
        transaccion.setIdTransaccion(UUID.randomUUID().toString());
        transaccion.setFecha(LocalDateTime.now());

        return transaccionRepository.save(transaccion);
    }

    public Transaccion actualizarTransaccion(String idTransaccion, Transaccion transaccionActualizada)
            throws ClienteNotFoundException {
        Transaccion transaccionExistente = obtenerTransaccionPorId(idTransaccion);

        transaccionExistente.setCliente(transaccionActualizada.getCliente());
        transaccionExistente.setTipoTransaccion(transaccionActualizada.getTipoTransaccion());
        transaccionExistente.setMonto(transaccionActualizada.getMonto());

        return transaccionRepository.save(transaccionExistente);
    }

    public void eliminarTransaccion(String idTransaccion) throws TransaccionNotFoundException {
        Transaccion transaccion = obtenerTransaccionPorId(idTransaccion);
        transaccionRepository.delete(transaccion);
    }
}
