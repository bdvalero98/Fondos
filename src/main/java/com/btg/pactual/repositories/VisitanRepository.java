package com.btg.pactual.repositories;

import com.btg.pactual.domain.models.Visitan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitanRepository extends MongoRepository<Visitan, String> {
    List<Visitan> findBySucursalPorId(String sucursalId);

    List<Visitan> findByClientePorId(String clienteId);
}
