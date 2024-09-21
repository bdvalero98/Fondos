package com.btg.fondos.application.services;

import com.btg.fondos.domain.exceptions.FondoNotFoundException;
import com.btg.fondos.domain.models.Fondo;
import com.btg.fondos.domain.repository.FondoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FondoService {

    private final FondoRepository fondoRepository;

    public List<Fondo> obtenerFondos() {
        return fondoRepository.findAll();
    }

    public Fondo obtenerFondoPorId(String id) {
        return fondoRepository.findById(id).
                orElseThrow(() -> new FondoNotFoundException("Fondo no encontrado con el id: " + id));
    }

    public Fondo guardarFondo(Fondo fondo) {
        return fondoRepository.save(fondo);
    }

    public void eliminarFondo(String id) {
        fondoRepository.deleteById(id);
    }

}
