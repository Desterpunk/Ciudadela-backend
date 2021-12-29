package co.com.sofka.api;

import co.com.sofka.model.material.Material;
import co.com.sofka.usecase.material.CreateMaterialUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final CreateMaterialUseCase createMaterialUseCase;

    public Mono<ServerResponse> createMaterial(ServerRequest serverRequest){
        return serverRequest.bodyToMono(Material.class).flatMap(createMaterialUseCase::createMaterial).flatMap(currentMaterial -> ServerResponse.ok()
                .bodyValue(currentMaterial));
    }
}
