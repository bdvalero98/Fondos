package com.btg.fondos.domain.repository;

import com.btg.fondos.domain.models.Fondo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FondoRepository extends MongoRepository<Fondo, String> {
}
