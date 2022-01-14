package co.com.sofka.usecase.tipoconstruccion;

import co.com.sofka.model.tipoconstruccion.TipoConstruccion;
import co.com.sofka.model.tipoconstruccion.gateways.TipoConstruccionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class FindAllTipoConstruccionUseCase {

    private final TipoConstruccionRepository tipoConstruccionRepository;

    public Flux<TipoConstruccion> findAllTipoConstruccion(){
        return tipoConstruccionRepository.findAllTipoConstruccion();
    }
}

