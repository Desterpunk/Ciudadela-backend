package co.com.sofka.usecase.compramaterial;

import co.com.sofka.model.compramaterial.CompraMaterial;
import co.com.sofka.model.compramaterial.gateways.CompraMaterialRepository;
import co.com.sofka.model.material.gateways.MaterialRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateCompraMaterialUseCase {
    private final CompraMaterialRepository compraMaterialRepository;
    private final MaterialRepository materialRepository;

    public Mono<CompraMaterial> createCompraMaterial(CompraMaterial compraMaterial){
        return compraMaterialRepository.createCompraMaterial(compraMaterial);
    }
}
