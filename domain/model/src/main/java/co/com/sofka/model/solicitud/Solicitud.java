package co.com.sofka.model.solicitud;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Solicitud {
    private String idSolicitud;
    private String idTipoDeConstruccion;
    private double x;
    private double y;
    private Date fechaInicio;
    private Date fechaEntrega;
}
