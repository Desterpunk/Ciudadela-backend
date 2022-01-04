package co.com.sofka.usecase.ordenConstruccion;

import co.com.sofka.model.ordenconstruccion.gateways.OrdenConstruccionRepository;
import co.com.sofka.model.solicitud.gateways.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
public class UpdateOrdenStatusUseCase {
    private final OrdenConstruccionRepository ordenConstruccionRepository;
    private final SolicitudRepository solicitudRepository;
    private final UpdateOrdenUseCase updateOrdenUseCase;

    public Mono<String> updateOrdenStatus(){

        return ordenConstruccionRepository.findAllOrdenConstruccion().flatMap(ordenConstruccion -> {
            return solicitudRepository.findById(ordenConstruccion.getIdSolicitud()).flatMap(solicitud -> {
                LocalDateTime fechaActual = LocalDateTime.now().withMinute(00).withSecond(00).withNano(00);
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                int horaActual = fechaActual.getHour();

                if (horaActual<12 && solicitud.getFechaInicio().format(formato).equals(fechaActual.format(formato))
                        && ordenConstruccion.getEstadoOrdenConstruccion().equals("pendiente")){
                    ordenConstruccionRepository.updateOrdenCOnstruccion(ordenConstruccion).subscribe();
                    ordenConstruccion.setEstadoOrdenConstruccion("progreso");
                    updateOrdenUseCase.updateOrden(ordenConstruccion).subscribe();
                    return Mono.just("Estado con la orden " + ordenConstruccion.getId() + " actualizado (progreso)");
                }

                if (horaActual>17 && solicitud.getFechaEntrega().format(formato).equals(fechaActual.format(formato))
                        && ordenConstruccion.getEstadoOrdenConstruccion().equals("progreso")){
                    System.out.println(ordenConstruccion);
                    ordenConstruccion.setId(ordenConstruccion.getId());
                    ordenConstruccion.setEstadoOrdenConstruccion("finalizado");
                    updateOrdenUseCase.updateOrden(ordenConstruccion).subscribe();
                    return Mono.just("Estado con la orden " + ordenConstruccion.getId() + " actualizado (finalizado)");
                }
                return Mono.just("Estado con la orden " + ordenConstruccion.getId() + " No a cambiado");
            });
        }).collectList().map(Object::toString);

    }
}
