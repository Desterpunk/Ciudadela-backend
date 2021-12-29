package co.com.sofka.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("solicitud")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class SolicitudData {
    private String id;
    private String idTipoConstruccion;
    private double x;
    private double y;
    private Date fechaInicio;
    private Date fechaEntrega;
}
