package co.com.sofka.model.compramaterial.gateways;

import co.com.sofka.model.compramaterial.CompraMaterial;
import reactor.core.publisher.Mono;

public interface CompraMaterialRepository {
    Mono<CompraMaterial> createCompraMaterial(CompraMaterial compraMaterial);
}
