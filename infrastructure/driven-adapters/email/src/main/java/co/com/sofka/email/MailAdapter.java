package co.com.sofka.email;

import co.com.sofka.model.notificacion.Notificacion;
import co.com.sofka.model.notificacion.gateways.NotificiacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MailAdapter implements NotificiacionRepository {

    @Autowired
    private JavaMailSender sender;

    @Override
    public Mono<String> notificar(Notificacion notificacion) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jhonalfaber@gmail.com");
        message.setTo(notificacion.getEmail());
        message.setSubject("Job Execution Result");
        message.setText("Status: ".concat(notificacion.getEstado()));
        sender.send(message);

        return Mono.just("Mail Enviado");
    }
}
