package co.com.sofka.usecase.compramaterial;

import co.com.sofka.model.material.gateways.MaterialRepository;
import co.com.sofka.model.tipoconstruccion.TipoConstruccion;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReducirMaterialSegunTipoUseCase {
    private final MaterialRepository materialRepository;

    public Mono<TipoConstruccion> reducirMaterialSegunTipo(TipoConstruccion tipoConstruccion){
        return materialRepository.findAllMaterial().flatMap(material -> {
            switch (material.getNombreMaterial()) {
                case "cemento":
                    if (material.getCantidadDisponibleMaterial() < tipoConstruccion.getCemento()){
                        return Mono.error(new RuntimeException("Cemento Agotado"));
                    }
                    material.setCantidadDisponibleMaterial(material.getCantidadDisponibleMaterial()-tipoConstruccion.getCemento());
                    return materialRepository.updateMaterial(material);
                case "grava":
                    if (material.getCantidadDisponibleMaterial() < tipoConstruccion.getGrava()){
                        return Mono.error(new RuntimeException("Grava Agotado"));
                    }
                    material.setCantidadDisponibleMaterial(material.getCantidadDisponibleMaterial()-tipoConstruccion.getGrava());
                    return materialRepository.updateMaterial(material);
                case "arena":
                    if (material.getCantidadDisponibleMaterial() < tipoConstruccion.getArena()){
                        return Mono.error(new RuntimeException("Arena Agotado"));
                    }
                    material.setCantidadDisponibleMaterial(material.getCantidadDisponibleMaterial()-tipoConstruccion.getArena());
                    return materialRepository.updateMaterial(material);
                case "madera":
                    if (material.getCantidadDisponibleMaterial() < tipoConstruccion.getMadera()){
                        return Mono.error(new RuntimeException("Madera Agotada"));
                    }
                    material.setCantidadDisponibleMaterial(material.getCantidadDisponibleMaterial()-tipoConstruccion.getMadera());
                    return materialRepository.updateMaterial(material);
                case "adobe":
                    if (material.getCantidadDisponibleMaterial() < tipoConstruccion.getAdobe()){
                        return Mono.error(new RuntimeException("Adobe Agotado"));
                    }
                    material.setCantidadDisponibleMaterial(material.getCantidadDisponibleMaterial()-tipoConstruccion.getAdobe());
                    return materialRepository.updateMaterial(material);
                default:
                    return Mono.error(new RuntimeException("Material no encontrado"));
            }

        }).collectList().map(resource -> {
            return tipoConstruccion;
        });
    }
}
