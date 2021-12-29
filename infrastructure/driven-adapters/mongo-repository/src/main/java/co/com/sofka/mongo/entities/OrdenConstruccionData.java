package co.com.sofka.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ordenConstruccion")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class OrdenConstruccionData {
    private String id;
    private String estadoOrdenConstruccion;
}
