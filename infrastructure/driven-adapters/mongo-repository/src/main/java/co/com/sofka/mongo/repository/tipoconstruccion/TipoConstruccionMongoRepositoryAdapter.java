package co.com.sofka.mongo.repository.tipoconstruccion;

import co.com.sofka.model.tipoconstruccion.TipoConstruccion;
import co.com.sofka.model.tipoconstruccion.gateways.TipoConstruccionRepository;
import co.com.sofka.mongo.entities.TipoConstruccionData;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class TipoConstruccionMongoRepositoryAdapter extends AdapterOperations<TipoConstruccion, TipoConstruccionData, String, TipoConstruccionMongoDBRepository>
        implements TipoConstruccionRepository {

    public TipoConstruccionMongoRepositoryAdapter(TipoConstruccionMongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, TipoConstruccion.class/* change for domain model */));
    }

    @Override
    public Mono<TipoConstruccion> createTipoConstruccion(TipoConstruccion tipoConstruccion) {
        TipoConstruccionData tipoConstruccionData = TipoConstruccionData.builder().nombreTipoConstruccion(tipoConstruccion.getNombreTipoConstruccion())
                .cemento(tipoConstruccion.getCemento())
                .grava(tipoConstruccion.getGrava())
                .arena(tipoConstruccion.getArena())
                .madera(tipoConstruccion.getMadera())
                .adobe(tipoConstruccion.getAdobe())
                .dias(tipoConstruccion.getDias())
                .build();
        return repository.save(tipoConstruccionData).map(this::toEntity);
    }
}
