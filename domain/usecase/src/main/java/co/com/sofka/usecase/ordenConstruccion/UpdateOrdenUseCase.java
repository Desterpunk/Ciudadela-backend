package co.com.sofka.usecase.ordenConstruccion;

import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.model.ordenconstruccion.gateways.OrdenConstruccionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateOrdenUseCase {

    private final OrdenConstruccionRepository ordenConstruccionRepository;

    public Mono<OrdenConstruccion> updateOrden(OrdenConstruccion ordenConstruccion){
        return ordenConstruccionRepository.updateOrdenCOnstruccion(ordenConstruccion);
    }
}
