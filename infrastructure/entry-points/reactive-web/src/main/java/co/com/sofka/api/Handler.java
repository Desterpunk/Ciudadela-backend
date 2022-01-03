package co.com.sofka.api;

import co.com.sofka.model.material.Material;
import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.model.solicitud.Solicitud;
import co.com.sofka.model.tipoconstruccion.TipoConstruccion;
import co.com.sofka.usecase.material.FindByNombreMaterialUseCase;
import co.com.sofka.usecase.ordenConstruccion.FindAllOrdenByEstadoUseCase;
import co.com.sofka.usecase.solicitud.FindAllSolicitudByEstadoUseCase;
import co.com.sofka.usecase.solicitud.FindByXAndYUseCase;
import co.com.sofka.usecase.solicitud.FindSolicitudByIdUseCase;
import co.com.sofka.usecase.tipoconstruccion.FindByNombreTipoConstruccionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final FindByNombreMaterialUseCase findByNombreMaterialUseCase;
    private final FindByNombreTipoConstruccionUseCase findByNombreTipoConstruccionUseCase;
    private final FindByXAndYUseCase findByXAndYUseCase;
    private final FindAllOrdenByEstadoUseCase findAllOrdenByEstadoUseCase;
    private final FindSolicitudByIdUseCase findSolicitudByIdUseCase;
    private final FindAllSolicitudByEstadoUseCase findAllSolicitudByEstadoUseCase;

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
}
