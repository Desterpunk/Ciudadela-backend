package co.com.sofka.api;

import co.com.sofka.model.material.Material;
import co.com.sofka.usecase.material.FindByNombreMaterialUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final FindByNombreMaterialUseCase findByNombreMaterialUseCase;

    public Mono<Material> findByNombreMaterial(String nombreMaterial) {
        Mono<Material> materialMono = findByNombreMaterialUseCase.findByNombreMaterial(nombreMaterial);
        return materialMono;
    }
}
