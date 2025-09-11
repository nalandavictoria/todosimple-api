package com.nalandavictoria.todosimple.rest.controller;

import com.nalandavictoria.todosimple.model.UserModel;
import com.nalandavictoria.todosimple.rest.dto.UserDTO;
import com.nalandavictoria.todosimple.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    @Validated(UserModel.UserCreate.class)
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserModel userModel) {
        UserDTO newUser = userService.create(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/{id}")
    @Validated(UserModel.UserUpdate.class)
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserModel userModel, @PathVariable Long id){
        UserDTO updateUser = userService.update(userModel);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}