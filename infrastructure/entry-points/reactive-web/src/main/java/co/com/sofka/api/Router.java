package co.com.sofka.api;

import co.com.sofka.model.compramaterial.CompraMaterial;
import co.com.sofka.model.material.Material;
import co.com.sofka.model.notificacion.Notificacion;
import co.com.sofka.model.ordenconstruccion.OrdenConstruccion;
import co.com.sofka.model.solicitud.Solicitud;
import co.com.sofka.model.tipoconstruccion.TipoConstruccion;
import co.com.sofka.usecase.compramaterial.CreateCompraMaterialUseCase;
import co.com.sofka.usecase.compramaterial.ReducirMaterialSegunTipoUseCase;
import co.com.sofka.usecase.material.CreateMaterialUseCase;
import co.com.sofka.usecase.material.FindAllMaterialUseCase;
import co.com.sofka.usecase.material.FindByNombreMaterialUseCase;
import co.com.sofka.usecase.material.UpdateMaterialUseCase;
import co.com.sofka.usecase.notificacion.NotificacionUseCase;
import co.com.sofka.usecase.ordenConstruccion.*;
import co.com.sofka.usecase.solicitud.*;
import co.com.sofka.usecase.tipoconstruccion.CreateTipoConstruccionUseCase;
import co.com.sofka.usecase.tipoconstruccion.FindByNombreTipoConstruccionUseCase;
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
    public RouterFunction<ServerResponse> routerFunction(FindByNombreMaterialUseCase findByNombreMaterialUseCase) {
        return route(GET("/material/{name}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(findByNombreMaterialUseCase.findByNombreMaterial(request.pathVariable("name")), Material.class));

    }

    @Bean
    public RouterFunction<ServerResponse> findbynombretipoconstruccion(FindByNombreTipoConstruccionUseCase findByNombreTipoConstruccionUseCase) {
        return route(GET("/tipoconstruccion/{tipo}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(findByNombreTipoConstruccionUseCase.findByNombreTipoConstruccion(request.pathVariable("tipo")), Material.class));

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
    public RouterFunction<ServerResponse> findByXandY(FindByXAndYUseCase findByXAndYUseCase) {
        return route(GET("/solicitud/{x}/{y}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(findByXAndYUseCase.findByXAndY(Double.parseDouble(request.pathVariable("x")),Double.parseDouble(request.pathVariable("y"))), Boolean.class));

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

    @Bean
    public RouterFunction<ServerResponse> findOrdenByEstado(FindAllOrdenByEstadoUseCase findAllOrdenByEstadoUseCase) {
        return route(GET("/orden/{estado}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(findAllOrdenByEstadoUseCase.findAllOrdenByProgress(request.pathVariable("estado")), Material.class));

    }

    @Bean
    public RouterFunction<ServerResponse> findSolicitudById(FindSolicitudByIdUseCase findSolicitudByIdUseCase) {
        return route(GET("/solicitud/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(findSolicitudByIdUseCase.findSolicitudById(request.pathVariable("id")), Material.class));

    }

    @Bean
    public RouterFunction<ServerResponse> findSolicitudByEstado(FindAllSolicitudByEstadoUseCase findAllSolicitudByEstadoUseCase) {
        return route(GET("/solicitudestado/{estado}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(findAllSolicitudByEstadoUseCase.findAllSolicitudByEstado(request.pathVariable("estado")), Material.class));

    }

    @Bean
    public RouterFunction<ServerResponse> findFechaEntregaSolicitud(FindFechaEntregaSolicitudUseCase findFechaEntregaSolicitudUseCase) {
        return route(GET("/fechaentregasolicitud/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(findFechaEntregaSolicitudUseCase.findSFechaEntregaSolicitud(request.pathVariable("id")), Material.class));

    }

    @Bean
    public RouterFunction<ServerResponse> createFileSolicitudEstado(CreateFileSolicitudByEstadoUseCase createFileSolicitudByEstadoUseCase) {
        return route(GET("/createfilesolicitud/{estado}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(createFileSolicitudByEstadoUseCase.createFileSolicitudByEstado(request.pathVariable("estado")), Material.class));

    }

    @Bean
    public RouterFunction<ServerResponse> getAllOrdenConstruccion(FindAllOrdenUseCase findAllOrdenUseCase){
        return route(GET("/getallorden").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(findAllOrdenUseCase.findAllOrden(), OrdenConstruccion.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateOrden(UpdateOrdenUseCase updateOrdenUseCase){
        return route(PUT("/updateOrden").and(accept(MediaType.APPLICATION_JSON)),
                request ->request.bodyToMono(OrdenConstruccion.class)
                        .flatMap(recursoDTO -> updateOrdenUseCase.updateOrden(recursoDTO)
                                .flatMap(result->ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result)))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> updateOrdenStatus(UpdateOrdenStatusUseCase updateOrdenStatusUseCase){
        return route(GET("/updateOrdenStatus").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(updateOrdenStatusUseCase.updateOrdenStatus(), String.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> sendNotification(NotificacionUseCase notificacionUseCase) {
        return route(POST("/sendNotification").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Notificacion.class)
                        .flatMap(resourceDTO -> notificacionUseCase.sendNotificacion(resourceDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }
}
