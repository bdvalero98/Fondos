package com.btg.pactual.repositories;

import com.btg.pactual.domain.models.Sucursal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends MongoRepository<Sucursal, String> {
}
