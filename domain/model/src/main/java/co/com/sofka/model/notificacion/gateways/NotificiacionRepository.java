package co.com.sofka.model.notificacion.gateways;

import co.com.sofka.model.notificacion.Notificacion;
import reactor.core.publisher.Mono;

public interface NotificiacionRepository {
    Mono<String> notificar(Notificacion notificacion);
}
