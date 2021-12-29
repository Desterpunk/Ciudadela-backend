package co.com.sofka.usecase.ordenConstruccion;

import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.model.ordenconstruccion.gateways.OrdenConstruccionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateOrdenConstruccionUseCase {

    private final OrdenConstruccionRepository ordenConstruccionRepository;

    public Mono<OrdenConstruccion> createOrdenConstruccion(OrdenConstruccion ordenConstruccion){
        return ordenConstruccionRepository.createOrdenConstruccion(ordenConstruccion);
    }
}
