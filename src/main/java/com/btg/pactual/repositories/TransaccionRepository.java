package com.btg.pactual.repositories;

import com.btg.pactual.domain.models.Cliente;
import com.btg.pactual.domain.models.Transaccion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionRepository extends MongoRepository<Transaccion, String> {

    List<Transaccion> findByCliente(Cliente cliente);

    List<Transaccion> findByClienteOrderByFechaDesc(Cliente cliente);
}
