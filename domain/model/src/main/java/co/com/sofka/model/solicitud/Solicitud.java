package co.com.sofka.model.solicitud;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Solicitud {
    private String id;
    private String tipoConstruccion;
    private Double x;
    private Double y;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaEntrega;
}
