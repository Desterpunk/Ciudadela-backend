package co.com.sofka.api;

import co.com.sofka.model.material.Material;
import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.model.solicitud.Solicitud;
import co.com.sofka.model.tipoconstruccion.TipoConstruccion;
import co.com.sofka.usecase.material.FindByNombreMaterialUseCase;
import co.com.sofka.usecase.ordenConstruccion.FindAllOrdenByEstadoUseCase;
import co.com.sofka.usecase.solicitud.*;
import co.com.sofka.usecase.tipoconstruccion.FindByNombreTipoConstruccionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Handler {

    private final FindByNombreMaterialUseCase findByNombreMaterialUseCase;
    private final FindByNombreTipoConstruccionUseCase findByNombreTipoConstruccionUseCase;
    private final FindByXAndYUseCase findByXAndYUseCase;
    private final FindAllOrdenByEstadoUseCase findAllOrdenByEstadoUseCase;
    private final FindSolicitudByIdUseCase findSolicitudByIdUseCase;
    private final FindAllSolicitudByEstadoUseCase findAllSolicitudByEstadoUseCase;
    private final FindFechaEntregaSolicitudUseCase findFechaEntregaSolicitudUseCase;
    private final CreateFileSolicitudByEstadoUseCase createFileSolicitudByEstadoUseCase;

    public Mono<Material> findByNombreMaterial(String nombreMaterial) {
        Mono<Material> materialMono = findByNombreMaterialUseCase.findByNombreMaterial(nombreMaterial);
        return materialMono;
    }

    public Mono<TipoConstruccion> findByNombreTipoConstruccion(String nombreTipoConstruccion) {
        Mono<TipoConstruccion> tipoConstruccionMono = findByNombreTipoConstruccionUseCase.findByNombreTipoConstruccion(nombreTipoConstruccion);
        return tipoConstruccionMono;
    }

    public Mono<Boolean> findByXandY(String x, String y) {
        Mono<Boolean> solicitudMono = findByXAndYUseCase.findByXAndY(Double.parseDouble(x),Double.parseDouble(y));
        return solicitudMono;
    }

    public Flux<OrdenConstruccion> findOrdenByEstado(String estado) {
        Flux<OrdenConstruccion> ordenConstruccionFlux = findAllOrdenByEstadoUseCase.findAllOrdenByProgress(estado);
        return ordenConstruccionFlux;
    }

    public Mono<Solicitud> findSolicitudById(String id) {
        Mono<Solicitud> solicitudMono = findSolicitudByIdUseCase.findSolicitudById(id);
        return solicitudMono;
    }

    public Flux<Solicitud> findSolicitudByEstado(String estado) {
        System.out.println(estado);
        Flux<Solicitud> solicitudFlux = findAllSolicitudByEstadoUseCase.findAllSolicitudByEstado(estado);
        return solicitudFlux;
    }

    public Mono<LocalDateTime> findFechaEntregaSolicitudById(String id) {
        Mono<LocalDateTime> localDateTimeMono = findFechaEntregaSolicitudUseCase.findSFechaEntregaSolicitud(id);
        return localDateTimeMono;
    }

    public Mono<String> createFileSolicitudByEstado(String estado) {
        Mono<String> stringMono = createFileSolicitudByEstadoUseCase.createFileSolicitudByEstado(estado);
        return stringMono;
    }
}
