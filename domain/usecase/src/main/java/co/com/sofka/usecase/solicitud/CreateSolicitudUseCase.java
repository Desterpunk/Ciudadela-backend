package co.com.sofka.usecase.solicitud;

import co.com.sofka.model.material.gateways.MaterialRepository;
import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.model.ordenconstruccion.gateways.OrdenConstruccionRepository;
import co.com.sofka.model.solicitud.Solicitud;
import co.com.sofka.model.solicitud.gateways.SolicitudRepository;
import co.com.sofka.model.tipoconstruccion.gateways.TipoConstruccionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateSolicitudUseCase {

    private final SolicitudRepository solicitudRepository;
    private final OrdenConstruccionRepository ordenConstruccionRepository;
    private final TipoConstruccionRepository tipoConstruccionRepository;
    private final MaterialRepository materialRepository;

    public Mono<Solicitud> createSolicitud(Solicitud solicitud){
        OrdenConstruccion ordenConstruccion = new OrdenConstruccion();
        ordenConstruccion.setIdSolicitud(solicitud.getId());

        tipoConstruccionRepository.findByNombreTipoConstruccion(solicitud.getTipoConstruccion()).flatMap(resource -> {
            System.out.println(resource);
            return Mono.just("hola");
        }).subscribe();

        return solicitudRepository.createSolicitud(solicitud);
    }
}
