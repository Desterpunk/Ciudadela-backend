package co.com.sofka.model.ordenconstruccion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrdenConstruccion {
    private String id;
    private String idSolicitud;
    private String estadoOrdenConstruccion;
}
