package co.com.sofka.mongo.repository.ordenConstruccion;

import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.mongo.entities.OrdenConstruccionData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Flux;

public interface OrdenConstruccionMongoDBRepository extends ReactiveMongoRepository<OrdenConstruccionData, String>, ReactiveQueryByExampleExecutor<OrdenConstruccionData> {
    Flux<OrdenConstruccion> findAllByEstadoOrdenConstruccion(String estadoOrdenConstruccion);
}
