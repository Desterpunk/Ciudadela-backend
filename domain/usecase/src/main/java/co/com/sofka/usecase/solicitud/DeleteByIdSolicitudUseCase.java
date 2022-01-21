package co.com.sofka.usecase.solicitud;

import co.com.sofka.model.solicitud.gateways.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteByIdSolicitudUseCase {

    private final SolicitudRepository solicitudRepository;

    public Mono<Void> deleteSolicitud(String id){
        return solicitudRepository.deleteById(id);
    }
}
