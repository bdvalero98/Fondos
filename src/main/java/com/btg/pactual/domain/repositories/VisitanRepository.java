package com.btg.pactual.domain.repositories;

import com.btg.pactual.domain.models.Visitan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitanRepository extends MongoRepository<Visitan, String> {
}
