package co.com.sofka.usecase.solicitud;

import co.com.sofka.model.material.gateways.MaterialRepository;
import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.model.ordenconstruccion.gateways.OrdenConstruccionRepository;
import co.com.sofka.model.solicitud.Solicitud;
import co.com.sofka.model.solicitud.gateways.SolicitudRepository;
import co.com.sofka.model.tipoconstruccion.gateways.TipoConstruccionRepository;
import co.com.sofka.usecase.compramaterial.ReducirMaterialSegunTipoUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateSolicitudUseCase {

    private final SolicitudRepository solicitudRepository;
    private final OrdenConstruccionRepository ordenConstruccionRepository;
    private final TipoConstruccionRepository tipoConstruccionRepository;
    private final MaterialRepository materialRepository;
    private final ReducirMaterialSegunTipoUseCase reducirMaterialSegunTipoUseCase;

    public Mono<Solicitud> createSolicitud(Solicitud solicitud){
        return solicitudRepository.findByXAndY(solicitud.getX(),solicitud.getY())
                .flatMap(solicitudByXandX -> Mono.error(new RuntimeException("Ya existe un/a " + solicitud.getTipoConstruccion() + " en esta ubicacion")).cast(Solicitud.class))
                .defaultIfEmpty(solicitud)
                .flatMap(currentSolicitud -> tipoConstruccionRepository.findByNombreTipoConstruccion(currentSolicitud.getTipoConstruccion()))
                .flatMap(reducirMaterialSegunTipoUseCase::reducirMaterialSegunTipo)
                .flatMap(currentSolicitud -> solicitudRepository.createSolicitud(solicitud))
                .flatMap(currentSolicitud -> {
                    solicitud.setId(currentSolicitud.getId());
                    OrdenConstruccion ordenConstruccion = new OrdenConstruccion();
                    ordenConstruccion.setIdSolicitud(currentSolicitud.getId());
                    ordenConstruccion.setEstadoOrdenConstruccion("Pendiente");
                    return ordenConstruccionRepository.createOrdenConstruccion(ordenConstruccion);
                }).flatMap(request -> Mono.just(solicitud));
    }
}
