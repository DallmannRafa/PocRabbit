package br.com.hbsis.pocrabbit.Cargo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICargoRepository extends MongoRepository<Cargo, String> {
}
