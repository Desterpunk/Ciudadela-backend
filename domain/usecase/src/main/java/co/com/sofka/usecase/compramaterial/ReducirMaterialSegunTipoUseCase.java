package co.com.sofka.usecase.compramaterial;

import co.com.sofka.model.material.gateways.MaterialRepository;
import co.com.sofka.model.tipoconstruccion.TipoConstruccion;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReducirMaterialSegunTipoUseCase {
    private final MaterialRepository materialRepository;

    public Mono<String> reducirMaterialSegunTipo(TipoConstruccion tipoConstruccion){
        return materialRepository.findAllMaterial().flatMap(material -> {
            switch (material.getNombreMaterial()) {
                case "cemento":
                    if (material.getCantidadDisponibleMaterial() < tipoConstruccion.getCemento()){
                        return Mono.error(new RuntimeException("Cemento Agotado"));
                    }
                    material.setCantidadDisponibleMaterial(material.getCantidadDisponibleMaterial()-tipoConstruccion.getCemento());
                    return materialRepository.updateMaterial(material);
                case "grava":
                    material.setCantidadDisponibleMaterial(material.getCantidadDisponibleMaterial()-tipoConstruccion.getGrava());
                    return materialRepository.updateMaterial(material);
                case "arena":
                    material.setCantidadDisponibleMaterial(material.getCantidadDisponibleMaterial()-tipoConstruccion.getArena());
                    return materialRepository.updateMaterial(material);
                case "madera":
                    material.setCantidadDisponibleMaterial(material.getCantidadDisponibleMaterial()-tipoConstruccion.getMadera());
                    return materialRepository.updateMaterial(material);
                case "adobe":
                    material.setCantidadDisponibleMaterial(material.getCantidadDisponibleMaterial()-tipoConstruccion.getAdobe());
                    return materialRepository.updateMaterial(material);
                default:
                    return Mono.error(new RuntimeException("Material no encontrado"));
            }

        }).collectList().map(resource -> {
            return "Enviado";
        });
    }
}
