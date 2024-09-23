package com.btg.pactual.services;

import com.btg.pactual.domain.models.Producto;
import com.btg.pactual.domain.models.Sucursal;
import com.btg.pactual.repositories.DisponibilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisponibilidadService {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    public Boolean productoDisponible(Producto producto, Sucursal sucursal) {
        return disponibilidadRepository.findByProductoYSucursal(producto, sucursal) != null;
    }
}
