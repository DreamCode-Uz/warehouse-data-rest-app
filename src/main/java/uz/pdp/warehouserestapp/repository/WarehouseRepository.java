package uz.pdp.warehouserestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.warehouserestapp.entity.Warehouse;
import uz.pdp.warehouserestapp.projection.AbsProjection;

@RepositoryRestResource(path = "warehouse", excerptProjection = AbsProjection.class)
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
}
