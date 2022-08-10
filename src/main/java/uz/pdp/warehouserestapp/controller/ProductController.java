package uz.pdp.warehouserestapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouserestapp.payload.ProductDTO;
import uz.pdp.warehouserestapp.service.ProductService;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> products() {
        return ok(service.products());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> product(@PathVariable Integer id) {
        return ok(service.product(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProductDTO dto) {
        return status(HttpStatus.CREATED).body(service.addProduct(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody ProductDTO dto) {
        return ok(service.editProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return service.deleteProduct(id);
    }
}
