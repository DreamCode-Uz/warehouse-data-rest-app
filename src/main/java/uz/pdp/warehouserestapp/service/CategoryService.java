package uz.pdp.warehouserestapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.warehouserestapp.config.NotFoundException;
import uz.pdp.warehouserestapp.entity.Category;
import uz.pdp.warehouserestapp.payload.CategoryDTO;
import uz.pdp.warehouserestapp.repository.CategoryRepository;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> getAllCategory(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page > 0 ? page - 1 : 0, size > 0 ? size : 10);
        return ok(repository.findAll(pageRequest));
    }

    public ResponseEntity<?> getOneCategory(Integer id) {
        Optional<Category> optionalCategory = repository.findById(id);
        if (!optionalCategory.isPresent()) throw new NotFoundException("Category not found");
        return ok(optionalCategory.get());
    }

    public ResponseEntity<?> addCategory(CategoryDTO dto) {
        Category category = new Category();
        if (dto.getCategoryId() != null) {
            Optional<Category> optionalCategory = repository.findById(dto.getCategoryId());
            optionalCategory.ifPresent(category::setCategory);
        }
        category.setName(dto.getName());
        category.setActive(dto.isActive());
        return status(HttpStatus.CREATED).body(repository.save(category));
    }

    public ResponseEntity<?> editCategory(Integer id, CategoryDTO dto) {
        Optional<Category> optionalCategory = repository.findById(id);
        if (!optionalCategory.isPresent()) throw new NotFoundException("Category not found");
        Category category = optionalCategory.get();
        if (dto.getCategoryId() != null) {
            Optional<Category> oc = repository.findById(dto.getCategoryId());
            oc.ifPresent(category::setCategory);
        } else category.setCategory(null);
        category.setName(dto.getName());
        category.setActive(dto.isActive());
        return ok(repository.save(category));
    }

    public ResponseEntity<?> deleteCategory(Integer id) {
        Optional<Category> optionalCategory = repository.findById(id);
        if (!optionalCategory.isPresent()) throw new NotFoundException("Category not found");
        repository.delete(optionalCategory.get());
        return noContent().build();
    }
}
