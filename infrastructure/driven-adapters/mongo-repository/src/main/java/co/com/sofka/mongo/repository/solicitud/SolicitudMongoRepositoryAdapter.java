package co.com.sofka.mongo.repository.solicitud;

import co.com.sofka.model.solicitud.Solicitud;
import co.com.sofka.model.solicitud.gateways.SolicitudRepository;
import co.com.sofka.mongo.entities.SolicitudData;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class SolicitudMongoRepositoryAdapter extends AdapterOperations<Solicitud, SolicitudData, String, SolicitudMongoDBRepository>
        implements SolicitudRepository {

    public SolicitudMongoRepositoryAdapter(SolicitudMongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Solicitud.class/* change for domain model */));
    }

    @Override
    public Mono<Solicitud> createSolicitud(Solicitud solicitud) {
        SolicitudData solicitudData = SolicitudData.builder().tipoConstruccion(solicitud.getTipoConstruccion())
                .x(solicitud.getX())
                .y(solicitud.getY())
                .fechaInicio(solicitud.getFechaInicio())
                .fechaEntrega(solicitud.getFechaEntrega())
                .build();
        return repository.save(solicitudData).map(this::toEntity);
    }
}