package co.com.sofka.model.compramaterial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CompraMaterial {
    private String id;
    private String material;
    private Double cantidadIngresada;
}
