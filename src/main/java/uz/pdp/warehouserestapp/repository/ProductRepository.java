package uz.pdp.warehouserestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.warehouserestapp.entity.Supplier;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Supplier, Integer> {
}
