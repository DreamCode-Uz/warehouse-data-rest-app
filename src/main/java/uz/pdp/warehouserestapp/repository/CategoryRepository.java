package uz.pdp.warehouserestapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.warehouserestapp.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
