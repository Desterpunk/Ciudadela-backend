package co.com.sofka.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("compraMaterial")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class CompraMaterialData {
    private String id;
    private String material;
    private Double cantidadIngresada;
}
