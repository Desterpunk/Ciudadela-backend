package co.com.sofka.mongo.repository.material;

import co.com.sofka.mongo.entities.MaterialData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MaterialMongoDBRepository extends ReactiveMongoRepository<MaterialData, String>, ReactiveQueryByExampleExecutor<MaterialData> {
}
