package uz.pdp.warehouserestapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouserestapp.payload.InputProductDTO;
import uz.pdp.warehouserestapp.service.InputProductService;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/input/product")
public class InputProductController {
    private final InputProductService service;

    @Autowired
    public InputProductController(InputProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> inputProducts() {
        return ok(service.inputProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> inputProduct(@PathVariable Integer id) {
        return ok(service.inputProduct(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody InputProductDTO dto) {
        return status(HttpStatus.CREATED).body(service.addInputProduct(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @Valid @RequestBody InputProductDTO dto) {
        return ok(service.editInputProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return service.deleteInputProduct(id);
    }
}
