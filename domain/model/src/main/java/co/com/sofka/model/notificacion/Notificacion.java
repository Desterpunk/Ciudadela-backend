package co.com.sofka.model.notificacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
    private String email;
    private String estado;
    private LocalDateTime fechaNotificacion;
}
