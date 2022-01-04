package co.com.sofka.usecase.notificacion;

import co.com.sofka.model.notificacion.Notificacion;
import co.com.sofka.model.notificacion.gateways.NotificiacionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class NotificacionUseCase {

    private final NotificiacionRepository notificacionRepository;

    public Mono<String> sendNotificacion(Notificacion notificacion){
        notificacion.setFechaNotificacion(LocalDateTime.now());
        return notificacionRepository.notificar(notificacion);
    }
}
