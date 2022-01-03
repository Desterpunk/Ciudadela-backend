package co.com.sofka.usecase.solicitud;

import co.com.sofka.model.solicitud.Solicitud;
import co.com.sofka.model.solicitud.gateways.SolicitudRepository;
import co.com.sofka.usecase.ordenConstruccion.FindAllOrdenByEstadoUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class FindAllSolicitudByEstadoUseCase {
    private final SolicitudRepository solicitudRepository;
    private final FindAllOrdenByEstadoUseCase findAllOrdenByEstadoUseCase;

    public Flux<Solicitud> findAllSolicitudByEstado(String estado){
        System.out.println(estado);
//        findAllOrdenByEstadoUseCase.findAllOrdenByProgress(estado.toLowerCase()).flatMap(ordenConstruccion -> {
//            System.out.println(ordenConstruccion.getIdSolicitud());
//            return solicitudRepository.findById(ordenConstruccion.getIdSolicitud());
//        }).subscribe();
        return solicitudRepository.findAllSolicitud();
    }
}
