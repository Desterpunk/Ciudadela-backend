package co.com.sofka.usecase.material;

import co.com.sofka.model.material.Material;
import co.com.sofka.model.material.gateways.MaterialRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class FindAllMaterialUseCase {

    private final MaterialRepository materialRepository;

    public Flux<Material> findAllMaterial(){
        return materialRepository.findAllMaterial();
    }
}
