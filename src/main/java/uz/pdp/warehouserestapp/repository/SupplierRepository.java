package uz.pdp.warehouserestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.warehouserestapp.entity.Supplier;
import uz.pdp.warehouserestapp.projection.SupplierProjection;

@RepositoryRestResource(path = "supplier", excerptProjection = SupplierProjection.class)
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
