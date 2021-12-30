package co.com.sofka.usecase.tipoconstruccion;

import co.com.sofka.model.tipoconstruccion.TipoConstruccion;
import co.com.sofka.model.tipoconstruccion.gateways.TipoConstruccionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindByNombreTipoConstruccionUseCase {
    private final TipoConstruccionRepository tipoConstruccionRepository;

    public Mono<TipoConstruccion> findByNombreTipoConstruccion(String nombreTipoConstruccion){
        return tipoConstruccionRepository.findByNombreTipoConstruccion(nombreTipoConstruccion);
    }
}
