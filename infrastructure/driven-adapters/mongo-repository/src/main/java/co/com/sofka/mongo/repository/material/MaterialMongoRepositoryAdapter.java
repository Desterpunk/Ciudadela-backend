package co.com.sofka.mongo.repository.material;

import co.com.sofka.model.material.Material;
import co.com.sofka.model.material.gateways.MaterialRepository;
import co.com.sofka.mongo.entities.MaterialData;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MaterialMongoRepositoryAdapter extends AdapterOperations<Material, MaterialData, String, MaterialMongoDBRepository>
        implements MaterialRepository {

    public MaterialMongoRepositoryAdapter(MaterialMongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Material.class/* change for domain model */));
    }

    @Override
    public Mono<Material> createMaterial(Material material) {
        MaterialData materialData = MaterialData.builder().nombreMaterial(material.getNombreMaterial())
                .cantidadDisponibleMaterial(material.getCantidadDisponibleMaterial())
                .build();
        return repository.save(materialData).map(this::toEntity);
    }

    @Override
    public Mono<Material> updateMaterial(Material material) {
        MaterialData materialData = MaterialData.builder().id(material.getId())
                .nombreMaterial(material.getNombreMaterial())
                .cantidadDisponibleMaterial(material.getCantidadDisponibleMaterial())
                .build();
        return repository.save(materialData).map(this::toEntity);
    }

    @Override
    public Mono<Material> findByNombreMaterial(String nombreMaterial) {
        return repository.findByNombreMaterial(nombreMaterial);
    }

    @Override
    public Flux<Material> findAllMaterial() {
        return repository.findAll().map(this::toEntity);
    }


}
