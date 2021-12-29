package co.com.sofka.usecase.material;

import co.com.sofka.model.material.Material;
import co.com.sofka.model.material.gateways.MaterialRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindByNombreMaterialUseCase {
    private final MaterialRepository materialRepository;

    public Mono<Material> findByNombreMaterial(String nombreMaterial){
        return materialRepository.findByNombreMaterial(nombreMaterial);
    }
}
