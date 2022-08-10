package uz.pdp.warehouserestapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouserestapp.payload.OutputDTO;
import uz.pdp.warehouserestapp.service.OutputService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/output")
public class OutputController {
    private final OutputService service;

    @Autowired
    public OutputController(OutputService service) {
        this.service = service;
    }

    @GetMapping
    private ResponseEntity<?> getAll() {
        return service.getAllOutput();
    }

    @GetMapping("/{outputId}")
    private ResponseEntity<?> getOne(@PathVariable("outputId") Integer id) {
        return service.getOneOutput(id);
    }

    @PostMapping
    private ResponseEntity<?> save(@Valid @RequestBody OutputDTO outputDTO) {
        return service.addOutput(outputDTO);
    }

    @PutMapping("/{outputId}")
    private ResponseEntity<?> update(@PathVariable("outputId") Integer id, @Valid @RequestBody OutputDTO outputDTO) {
        return service.editOutput(id, outputDTO);
    }

    @DeleteMapping("/{outputId}")
    private ResponseEntity<?> delete(@PathVariable("outputId") Integer id) {
        return service.deleteOutput(id);
    }
}
