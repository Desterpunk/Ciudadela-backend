package co.com.sofka.api;

import co.com.sofka.model.material.Material;
import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.usecase.material.CreateMaterialUseCase;
import co.com.sofka.usecase.ordenConstruccion.CreateOrdenConstruccionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> createMaterial(CreateMaterialUseCase createMaterialUseCase) {
        return route(POST("/material/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Material.class)
                        .flatMap(resourceDTO -> createMaterialUseCase.createMaterial(resourceDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> createOrdenConstruccion(CreateOrdenConstruccionUseCase createOrdenConstruccionUseCase) {
        return route(POST("/orden/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(OrdenConstruccion.class)
                        .flatMap(resourceDTO -> createOrdenConstruccionUseCase.createOrdenConstruccion(resourceDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/material/{name}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(handler.findByNombreMaterial(request.pathVariable("name")), Material.class));

    }
}
