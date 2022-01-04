package co.com.sofka.usecase.ordenConstruccion;

import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.model.ordenconstruccion.gateways.OrdenConstruccionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class FindAllOrdenUseCase {

    private final OrdenConstruccionRepository ordenConstruccionRepository;

    public Flux<OrdenConstruccion> findAllOrden(){
        return ordenConstruccionRepository.findAllOrdenConstruccion();
    }
}
