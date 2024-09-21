package com.btg.pactual.domain.repositories;

import com.btg.pactual.domain.models.Disponibilidad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilidadRepository extends MongoRepository<Disponibilidad, String> {
}
