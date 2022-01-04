package co.com.sofka.mongo.repository.ordenConstruccion;

import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.model.ordenconstruccion.gateways.OrdenConstruccionRepository;
import co.com.sofka.mongo.entities.OrdenConstruccionData;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class OrdenConstruccionMongoRepositoryAdapter extends AdapterOperations<OrdenConstruccion, OrdenConstruccionData, String, OrdenConstruccionMongoDBRepository>
        implements OrdenConstruccionRepository {

    public OrdenConstruccionMongoRepositoryAdapter(OrdenConstruccionMongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, OrdenConstruccion.class/* change for domain model */));
    }

    @Override
    public Mono<OrdenConstruccion> createOrdenConstruccion(OrdenConstruccion ordenConstruccion) {
        OrdenConstruccionData ordenConstruccionData = OrdenConstruccionData.builder().idSolicitud(ordenConstruccion.getIdSolicitud())
                .estadoOrdenConstruccion(ordenConstruccion.getEstadoOrdenConstruccion())
                .build();
        return repository.save(ordenConstruccionData).map(this::toEntity);
    }

    @Override
    public Flux<OrdenConstruccion> findAllByEstadoOrdenConstruccion(String estadoOrdenConstruccion) {
        return repository.findAllByEstadoOrdenConstruccion(estadoOrdenConstruccion);
    }

    @Override
    public Flux<OrdenConstruccion> findAllOrdenConstruccion(){
        return repository.findAll().map(this::toEntity);
    }

    @Override
    public Mono<OrdenConstruccion> updateOrdenCOnstruccion(OrdenConstruccion ordenConstruccion) {
        OrdenConstruccionData ordenConstruccionData = OrdenConstruccionData.builder().id(ordenConstruccion.getId())
                .idSolicitud(ordenConstruccion.getIdSolicitud())
                .estadoOrdenConstruccion(ordenConstruccion.getEstadoOrdenConstruccion())
                .build();
        return repository.save(ordenConstruccionData).map(this::toEntity);
    }
}

