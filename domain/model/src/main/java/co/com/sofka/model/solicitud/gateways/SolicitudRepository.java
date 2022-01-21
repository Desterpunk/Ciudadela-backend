package co.com.sofka.model.solicitud.gateways;

import co.com.sofka.model.solicitud.Solicitud;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SolicitudRepository {
    Mono<Solicitud> createSolicitud(Solicitud solicitud);
    Mono<Solicitud> updateSolicitud(Solicitud solicitud);
    Mono<Solicitud> findByPosicionCardinalXAndPosicionCardinalY(Double posicionCardinalX, Double posicionCardinalY);
    Flux<Solicitud> findAllSolicitud();
    Mono<Solicitud> findById(String id);
    Mono<Void> deleteById(String id);
}
