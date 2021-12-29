package co.com.sofka.mongo.repository.tipoconstruccion;

import co.com.sofka.mongo.entities.TipoConstruccionData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface TipoConstruccionMongoDBRepository extends ReactiveMongoRepository<TipoConstruccionData, String>, ReactiveQueryByExampleExecutor<TipoConstruccionData> {
}
