package co.com.sofka.usecase.ordenConstruccion;

import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.model.ordenconstruccion.gateways.OrdenConstruccionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class FindAllOrdenByEstadoUseCase {

    private final OrdenConstruccionRepository ordenConstruccionRepository;

    public Flux<OrdenConstruccion> findAllOrdenByProgress(String estado){
        return ordenConstruccionRepository.findAllByEstadoOrdenConstruccion(estado.toLowerCase());
    }
}
