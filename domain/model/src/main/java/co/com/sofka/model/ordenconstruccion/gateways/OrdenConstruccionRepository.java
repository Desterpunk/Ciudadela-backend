package co.com.sofka.model.ordenconstruccion.gateways;

import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrdenConstruccionRepository {
    Mono<OrdenConstruccion> createOrdenConstruccion(OrdenConstruccion ordenConstruccion);
    Flux<OrdenConstruccion> findAllByEstadoOrdenConstruccion(String estadoOrdenConstruccion);
    Flux<OrdenConstruccion> findAllOrdenConstruccion();
    Mono<OrdenConstruccion> updateOrdenCOnstruccion(OrdenConstruccion ordenConstruccion);
}
