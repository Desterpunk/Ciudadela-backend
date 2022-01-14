package co.com.sofka.model.tipoconstruccion.gateways;

import co.com.sofka.model.tipoconstruccion.TipoConstruccion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TipoConstruccionRepository {
    Mono<TipoConstruccion> createTipoConstruccion(TipoConstruccion tipoConstruccion);
    Mono<TipoConstruccion> findByNombreTipoConstruccion(String nombreTipoConstruccion);
    Flux<TipoConstruccion> findAllTipoConstruccion();
}
