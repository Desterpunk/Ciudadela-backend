package co.com.sofka.mongo.repository.compramaterial;

import co.com.sofka.model.compramaterial.CompraMaterial;
import co.com.sofka.model.compramaterial.gateways.CompraMaterialRepository;
import co.com.sofka.mongo.entities.CompraMaterialData;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class CompraMaterialMongoRepositoryAdapter extends AdapterOperations<CompraMaterial, CompraMaterialData, String, CompraMaterialMongoDBRepository>
        implements CompraMaterialRepository {

    public CompraMaterialMongoRepositoryAdapter(CompraMaterialMongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, CompraMaterial.class/* change for domain model */));
    }

    @Override
    public Mono<CompraMaterial> createCompraMaterial(CompraMaterial compraMaterial) {
        CompraMaterialData compraMaterialData = CompraMaterialData.builder().material(compraMaterial.getMaterial())
                .cantidadIngresada(compraMaterial.getCantidadIngresada())
                .build();
        return repository.save(compraMaterialData).map(this::toEntity);
    }
}