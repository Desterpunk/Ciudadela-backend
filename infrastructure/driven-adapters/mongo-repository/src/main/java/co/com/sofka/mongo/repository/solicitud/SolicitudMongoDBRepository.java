package co.com.sofka.mongo.repository.solicitud;

import co.com.sofka.model.solicitud.Solicitud;
import co.com.sofka.mongo.entities.SolicitudData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Mono;

public interface SolicitudMongoDBRepository extends ReactiveMongoRepository<SolicitudData, String>, ReactiveQueryByExampleExecutor<SolicitudData> {
    Mono<Solicitud> findByXAndY(Double x, Double y);
}
