package co.com.sofka.mongo.repository.ordenConstruccion;

import co.com.sofka.mongo.entities.OrdenConstruccionData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface OrdenConstruccionMongoDBRepository extends ReactiveMongoRepository<OrdenConstruccionData, String>, ReactiveQueryByExampleExecutor<OrdenConstruccionData> {
}
