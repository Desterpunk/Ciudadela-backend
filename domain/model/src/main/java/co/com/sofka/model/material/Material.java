package co.com.sofka.model.material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    private String id;
    private String nombreMaterial;
    private Double cantidadDisponibleMaterial;
}
