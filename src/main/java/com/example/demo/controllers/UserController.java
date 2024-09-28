package com.example.demo.controllers;
import com.example.demo.services.interfaces.UserService;
import com.example.demo.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);  // Devuelve el usuario con el código HTTP 200 OK
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> getUsers(@RequestParam String prefix) {
        List<User> users = userService.getUsersFiltered(prefix);
        return ResponseEntity.ok(users);  // Devuelve el usuario con el código HTTP 200 OK
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)  // Devuelve el código 201 Created
                .body(createdUser);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @Valid @RequestBody User userDetails) {
        User updatedUser = userService.updateUserById(id, userDetails);
        return ResponseEntity.ok(updatedUser);  // Devuelve 200 OK con el usuario actualizado
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();  // Devuelve 204 No Content
    }

}
