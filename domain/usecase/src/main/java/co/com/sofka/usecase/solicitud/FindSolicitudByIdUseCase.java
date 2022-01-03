package co.com.sofka.usecase.solicitud;

import co.com.sofka.model.solicitud.Solicitud;
import co.com.sofka.model.solicitud.gateways.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindSolicitudByIdUseCase {

    private final SolicitudRepository solicitudRepository;

    public Mono<Solicitud> findSolicitudById(String id){
        return solicitudRepository.findById(id);
    }
}
