package co.com.sofka.usecase.solicitud;


import co.com.sofka.model.solicitud.gateways.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class FindFechaEntregaSolicitudUseCase {

    private final SolicitudRepository solicitudRepository;

    public Mono<LocalDateTime> findSFechaEntregaSolicitud(String id){
        return solicitudRepository.findById(id).flatMap(solicitud -> {
            LocalDateTime fechaEntrega = solicitud.getFechaEntrega();
            return Mono.just(fechaEntrega);
        });
    }
}
