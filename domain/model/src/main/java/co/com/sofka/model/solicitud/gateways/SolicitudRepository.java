package co.com.sofka.model.solicitud.gateways;

import co.com.sofka.model.solicitud.Solicitud;
import reactor.core.publisher.Mono;

public interface SolicitudRepository {
    Mono<Solicitud> createSolicitud(Solicitud solicitud);
    Mono<Solicitud> findByXAndY(Double x, Double y);
}
