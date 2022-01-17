package co.com.sofka.usecase.solicitud;

import co.com.sofka.model.solicitud.gateways.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindByXAndYUseCase {

    private final SolicitudRepository solicitudRepository;

    public Mono<Boolean> findByXAndY(Double posicionCardinalX, Double posicionCardinalY) {
        return solicitudRepository.findByPosicionCardinalXAndPosicionCardinalY(posicionCardinalX,posicionCardinalY).flatMap(solicitud -> Mono.just(true)).switchIfEmpty(Mono.just(false)).cast(Boolean.class);
    }
}
