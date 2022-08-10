package uz.pdp.warehouserestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.warehouserestapp.entity.Measurement;
import uz.pdp.warehouserestapp.projection.AbsProjection;

@RepositoryRestResource(path = "measurement", excerptProjection = AbsProjection.class)
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
