package co.com.sofka.config;

import co.com.sofka.model.material.gateways.MaterialRepository;
import co.com.sofka.model.ordenconstruccion.gateways.OrdenConstruccionRepository;
import co.com.sofka.usecase.material.CreateMaterialUseCase;
import co.com.sofka.usecase.material.FindByNombreMaterialUseCase;
import co.com.sofka.usecase.ordenConstruccion.CreateOrdenConstruccionUseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.sofka.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {
        public CreateMaterialUseCase createMaterialUseCase(MaterialRepository materialRepository) {
                return new CreateMaterialUseCase(materialRepository);
        }
        public FindByNombreMaterialUseCase findByNombreMaterialUseCase(MaterialRepository materialRepository) {
                return new FindByNombreMaterialUseCase(materialRepository);
        }
        public CreateOrdenConstruccionUseCase createOrdenConstruccionUseCase(OrdenConstruccionRepository ordenConstruccionRepository){
                return new CreateOrdenConstruccionUseCase(ordenConstruccionRepository);
        }
}
