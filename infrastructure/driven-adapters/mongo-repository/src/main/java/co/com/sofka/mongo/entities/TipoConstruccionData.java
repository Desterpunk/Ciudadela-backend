package co.com.sofka.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tipoConstruccion")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class TipoConstruccionData {
    private String id;
    private String nombreTipoConstruccion;
    private Double cemento;
    private Double grava;
    private Double arena;
    private Double madera;
    private Double adobe;
    private Integer dias;
}
