package co.com.sofka.api;

import co.com.sofka.model.compramaterial.CompraMaterial;
import co.com.sofka.model.material.Material;
import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.model.solicitud.Solicitud;
import co.com.sofka.model.tipoconstruccion.TipoConstruccion;
import co.com.sofka.usecase.compramaterial.CreateCompraMaterialUseCase;
import co.com.sofka.usecase.compramaterial.ReducirMaterialSegunTipoUseCase;
import co.com.sofka.usecase.material.CreateMaterialUseCase;
import co.com.sofka.usecase.material.FindAllMaterialUseCase;
import co.com.sofka.usecase.material.UpdateMaterialUseCase;
import co.com.sofka.usecase.ordenConstruccion.CreateOrdenConstruccionUseCase;
import co.com.sofka.usecase.solicitud.CreateSolicitudUseCase;
import co.com.sofka.usecase.solicitud.FindAllSolicitudUseCase;
import co.com.sofka.usecase.solicitud.UpdateSolicitudUseCase;
import co.com.sofka.usecase.tipoconstruccion.CreateTipoConstruccionUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


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
    public RouterFunction<ServerResponse> createSolicitud(CreateSolicitudUseCase createSolicitudUseCase,CreateOrdenConstruccionUseCase createOrdenConstruccionUseCase) {
        return route(POST("/solicitud/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Solicitud.class)
                        .flatMap(resourceDTO -> createSolicitudUseCase.createSolicitud(resourceDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.badRequest().bodyValue(Map.of("Error","" + throwable.getMessage())))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> createTipoConstruccion(CreateTipoConstruccionUseCase createTipoConstruccionUseCase) {
        return route(POST("/tipoconstruccion/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TipoConstruccion.class)
                        .flatMap(resourceDTO -> createTipoConstruccionUseCase.createTipoConstruccion(resourceDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> createCompraMaterial(CreateCompraMaterialUseCase createCompraMaterialUseCase) {
        return route(POST("/compramaterial/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CompraMaterial.class)
                        .flatMap(resourceDTO -> createCompraMaterialUseCase.createCompraMaterial(resourceDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateResource(UpdateMaterialUseCase updateMaterialUseCase){
        return route(PUT("/material").and(accept(MediaType.APPLICATION_JSON)),
                request ->request.bodyToMono(Material.class)
                        .flatMap(recursoDTO -> updateMaterialUseCase.updateMaterial(recursoDTO)
                                .flatMap(result->ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateSolicitud(UpdateSolicitudUseCase updateSolicitudUseCase){
        return route(PUT("/solicitud").and(accept(MediaType.APPLICATION_JSON)),
                request ->request.bodyToMono(Solicitud.class)
                        .flatMap(recursoDTO -> updateSolicitudUseCase.updateSolicitud(recursoDTO)
                                .flatMap(result->ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/material/{name}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(handler.findByNombreMaterial(request.pathVariable("name")), Material.class));

    }

    @Bean
    public RouterFunction<ServerResponse> findbynombretipoconstruccion(Handler handler) {
        return route(GET("/tipoconstruccion/{tipo}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(handler.findByNombreTipoConstruccion(request.pathVariable("tipo")), Material.class));

    }
    @Bean
    public RouterFunction<ServerResponse> getAllMaterial(FindAllMaterialUseCase findAllMaterialUseCase){
        return route(GET("/material").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findAllMaterialUseCase.findAllMaterial(), Material.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> findByXandY(Handler handler) {
        return route(GET("/solicitud/{x}/{y}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(handler.findByXandY(request.pathVariable("x"),request.pathVariable("y")), Boolean.class));

    }

    @Bean
    public RouterFunction<ServerResponse> reducirMaterial(ReducirMaterialSegunTipoUseCase reducirMaterialSegunTipoUseCase) {
        return route(POST("/reducirmaterial/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(TipoConstruccion.class)
                        .flatMap(resourceDTO -> reducirMaterialSegunTipoUseCase.reducirMaterialSegunTipo(resourceDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }
    @Bean
    public RouterFunction<ServerResponse> getAllSolicitud(FindAllSolicitudUseCase findAllSolicitudUseCase){
        return route(GET("/solicitud").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findAllSolicitudUseCase.findAllSolicitud(), Solicitud.class))
        );
    }
}
