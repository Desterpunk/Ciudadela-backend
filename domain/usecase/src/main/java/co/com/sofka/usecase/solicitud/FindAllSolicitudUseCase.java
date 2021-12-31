package co.com.sofka.usecase.solicitud;

import co.com.sofka.model.solicitud.Solicitud;
import co.com.sofka.model.solicitud.gateways.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class FindAllSolicitudUseCase
{
    private final SolicitudRepository solicitudRepository;

    public Flux<Solicitud> findAllSolicitud(){
        return solicitudRepository.findAllSolicitud();
    }
}
