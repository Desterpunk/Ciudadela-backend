package co.com.sofka.mongo.repository.compramaterial;

import co.com.sofka.mongo.entities.CompraMaterialData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface CompraMaterialMongoDBRepository extends ReactiveMongoRepository<CompraMaterialData, String>, ReactiveQueryByExampleExecutor<CompraMaterialData> {
}
