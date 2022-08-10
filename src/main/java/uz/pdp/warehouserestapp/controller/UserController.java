package uz.pdp.warehouserestapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouserestapp.payload.UserDTO;
import uz.pdp.warehouserestapp.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    private ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        return service.getAllUsers(page);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<?> getOne(@PathVariable("userId") Integer userId) {
        return service.getOneUser(userId);
    }

    @PostMapping
    private ResponseEntity<?> save(@Valid @RequestBody UserDTO userDTO) {
        return service.addUser(userDTO);
    }

    @PutMapping("/{userId}")
    private ResponseEntity<?> update(@PathVariable("userId") Integer userId, @Valid @RequestBody UserDTO userDTO) {
        return service.editUser(userId, userDTO);
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<?> delete(@PathVariable("userId") Integer userId) {
        return service.deleteUser(userId);
    }
}
