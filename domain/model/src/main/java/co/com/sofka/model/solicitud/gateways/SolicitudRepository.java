package co.com.sofka.model.solicitud.gateways;

import co.com.sofka.model.solicitud.Solicitud;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SolicitudRepository {
    Mono<Solicitud> createSolicitud(Solicitud solicitud);
    Mono<Solicitud> updateSolicitud(Solicitud solicitud);
    Mono<Solicitud> findByXAndY(Double x, Double y);
    Flux<Solicitud> findAllSolicitud();
    Mono<Solicitud> findById(String id);
}
