package com.btg.pactual.services;

import com.btg.pactual.domain.exceptions.ClienteNotFoundException;
import com.btg.pactual.domain.exceptions.InscripcionNotFoundException;
import com.btg.pactual.domain.models.Cliente;
import com.btg.pactual.domain.models.Inscripcion;
import com.btg.pactual.repositories.ClienteRepository;
import com.btg.pactual.repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Inscripcion> obtenerTodasLasInscripciones() {
        return inscripcionRepository.findAll();
    }

    public Inscripcion obtenerInscripcionPorId(String idInscripcion) throws InscripcionNotFoundException {

        Optional<Inscripcion> inscripcionOptional = inscripcionRepository.findById(idInscripcion);

        if (inscripcionOptional.isPresent()) {
            return inscripcionOptional.get();
        } else {
            throw new InscripcionNotFoundException("Inscripcion con ID " + idInscripcion + " no encontrado");
        }
    }

    public Inscripcion crearInscripcion(Inscripcion inscripcion) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(inscripcion.getCliente().getIdCliente())
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con Id " +
                        inscripcion.getCliente().getIdCliente() + " no encontrado"));

        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion actualizarInscripcion(String idInscripcion,
                                             Inscripcion inscripcionActualizada) throws ClienteNotFoundException {

        Inscripcion inscripcionExistente = obtenerInscripcionPorId(idInscripcion);

        inscripcionExistente.setCliente(inscripcionActualizada.getCliente());
        inscripcionExistente.setProducto(inscripcionActualizada.getProducto());

        return inscripcionRepository.save(inscripcionExistente);
    }

    public void eliminarInscripcion(String idInscripcion) throws InscripcionNotFoundException {
        Inscripcion inscripcionExistente = obtenerInscripcionPorId(idInscripcion);
        inscripcionRepository.delete(inscripcionExistente);
    }

    public void cancelarInscripcionDeCliente(String idCliente,
                                             String idInscripcion) throws ClienteNotFoundException,
            InscripcionNotFoundException {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + idCliente + " no encontrado."));

        boolean inscripcionExiste = cliente.getInscripciones().removeIf(inscripcion ->
                inscripcion.getIdInscripcion().equals(idInscripcion));

        if (!inscripcionExiste) {
            throw new InscripcionNotFoundException("Inscripcion con ID " + idInscripcion + " no encontrado.");
        }

        clienteRepository.save(cliente);
    }
}
