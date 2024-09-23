package com.btg.pactual.repositories;

import com.btg.pactual.domain.models.Disponibilidad;
import com.btg.pactual.domain.models.Producto;
import com.btg.pactual.domain.models.Sucursal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilidadRepository extends MongoRepository<Disponibilidad, String> {

    Boolean findByProductoYSucursal(Producto producto, Sucursal sucursal);
}
