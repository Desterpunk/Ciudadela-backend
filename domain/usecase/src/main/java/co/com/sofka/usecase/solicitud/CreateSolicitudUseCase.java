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

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CreateSolicitudUseCase {

    private final SolicitudRepository solicitudRepository;
    private final OrdenConstruccionRepository ordenConstruccionRepository;
    private final TipoConstruccionRepository tipoConstruccionRepository;
    private final MaterialRepository materialRepository;
    private final ReducirMaterialSegunTipoUseCase reducirMaterialSegunTipoUseCase;

    public Mono<Solicitud> createSolicitud(Solicitud solicitud){
        final LocalDateTime[] dateTime = {LocalDateTime.now().plusDays(1).withHour(8).withMinute(00).withSecond(00)};
        return solicitudRepository.findByXAndY(solicitud.getX(),solicitud.getY())
                .flatMap(solicitudByXandX -> Mono.error(new RuntimeException("Ya existe un/a " + solicitud.getTipoConstruccion() + " en esta ubicacion")).cast(Solicitud.class))
                .defaultIfEmpty(solicitud)
                .flatMap(currentSolicitud -> tipoConstruccionRepository.findByNombreTipoConstruccion(currentSolicitud.getTipoConstruccion()))
                .flatMap(reducirMaterialSegunTipoUseCase::reducirMaterialSegunTipo)
                .flatMap(tipoConstruccion -> {
                    return solicitudRepository.createSolicitud(solicitud);
                })
                .flatMap(currentSolicitud -> {
                    return solicitudRepository.findAllSolicitud().flatMap(solicitudes -> {
                        System.out.println(solicitudes);
                        solicitud.setId(solicitudes.getId());
                        if (solicitudes.getFechaInicio() == null){

                            solicitudes.setFechaInicio(dateTime[0].withHour(8));
                            solicitud.setFechaInicio(dateTime[0].withHour(8));
                        }
                        dateTime[0] = solicitudes.getFechaInicio();

                        if (solicitudes.getFechaEntrega() == null){
                            return tipoConstruccionRepository.findByNombreTipoConstruccion(currentSolicitud.getTipoConstruccion()).map(tipoConstruccion -> {
                                solicitudes.setFechaEntrega(dateTime[0].plusDays(tipoConstruccion.getDias()).withHour(17));
                                solicitud.setFechaEntrega(dateTime[0].plusDays(tipoConstruccion.getDias()).withHour(17));
                                return tipoConstruccion;
                            }).flatMap(resurce -> {return solicitudRepository.updateSolicitud(solicitudes);}).map(resource -> {
                                return Mono.just(solicitudes);
                            });
                        }
                        dateTime[0] = solicitudes.getFechaEntrega().plusDays(1);

                        return Mono.just(solicitudes);
                    }).collectList().map(resource -> {
                        return solicitud;
                    });})
                .flatMap(currentSolicitud -> {
                    OrdenConstruccion ordenConstruccion = new OrdenConstruccion();
                    ordenConstruccion.setIdSolicitud(currentSolicitud.getId());
                    ordenConstruccion.setEstadoOrdenConstruccion("pendiente");
                    return ordenConstruccionRepository.createOrdenConstruccion(ordenConstruccion);
                }).flatMap(request -> Mono.just(solicitud));
    }
}
