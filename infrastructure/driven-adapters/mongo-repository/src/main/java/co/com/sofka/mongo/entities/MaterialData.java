package co.com.sofka.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("material")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class MaterialData {
    private String id;
    private String nombreMaterial;
    private Double cantidadDisponibleMaterial;
}