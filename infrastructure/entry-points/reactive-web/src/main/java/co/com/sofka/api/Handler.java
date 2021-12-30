package co.com.sofka.api;

import co.com.sofka.model.material.Material;
import co.com.sofka.model.tipoconstruccion.TipoConstruccion;
import co.com.sofka.usecase.material.FindByNombreMaterialUseCase;
import co.com.sofka.usecase.solicitud.FindByXAndYUseCase;
import co.com.sofka.usecase.tipoconstruccion.FindByNombreTipoConstruccionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final FindByNombreMaterialUseCase findByNombreMaterialUseCase;
    private final FindByNombreTipoConstruccionUseCase findByNombreTipoConstruccionUseCase;
    private final FindByXAndYUseCase findByXAndYUseCase;

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
}
