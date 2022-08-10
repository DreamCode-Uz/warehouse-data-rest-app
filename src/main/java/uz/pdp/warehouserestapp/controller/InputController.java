package uz.pdp.warehouserestapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouserestapp.payload.InputDTO;
import uz.pdp.warehouserestapp.service.InputService;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/input")
public class InputController {
    private final InputService service;

    @Autowired
    public InputController(InputService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> inputs() {
        return ok(service.inputs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> input(@PathVariable Integer id) {
        return ok(service.input(id));
    }

    @PostMapping
    public ResponseEntity<?> update(@Valid @RequestBody InputDTO dto) {
        return status(HttpStatus.CREATED).body(service.addInput(dto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody InputDTO dto) {
        return ok(service.editInput(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return service.deleteInput(id);
    }
}
