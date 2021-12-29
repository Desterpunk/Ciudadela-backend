package co.com.sofka.model.material.gateways;

import co.com.sofka.model.material.Material;
import reactor.core.publisher.Mono;

public interface MaterialRepository {
    Mono<Material> createMaterial(Material material);
    Mono<Material> findByNombreMaterial(String nombreMaterial);
}
