package com.btg.pactual.repositories;

import com.btg.pactual.domain.models.Inscripcion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends MongoRepository<Inscripcion, String> {
    List<Inscripcion> findByClientId(String clienteId);

    List<Inscripcion> findActivasPorCliente(String clienteId);


}
