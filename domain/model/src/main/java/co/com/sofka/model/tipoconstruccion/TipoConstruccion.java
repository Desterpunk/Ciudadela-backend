package co.com.sofka.model.tipoconstruccion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TipoConstruccion {
    private String idTipoConstruccion;
    private String nombreTipoConstruccion;
    private Double cemento;
    private Double grava;
    private Double arena;
    private Double madera;
    private Double adobe;
    private Integer dias;
}
