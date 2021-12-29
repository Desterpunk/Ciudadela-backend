package co.com.sofka.mongo.repository.solicitud;

import co.com.sofka.mongo.entities.SolicitudData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface SolicitudMongoDBRepository extends ReactiveMongoRepository<SolicitudData, String>, ReactiveQueryByExampleExecutor<SolicitudData> {
}
