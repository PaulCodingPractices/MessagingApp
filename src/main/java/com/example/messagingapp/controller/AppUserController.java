package com.example.messagingapp.controller;

import com.example.messagingapp.entity.User;
import com.example.messagingapp.mapping.UserDTO;
import com.example.messagingapp.mapping.UserMapping;
import com.example.messagingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = UserMapping.INSTANCE.toEntity(userDTO);
        User createdUser = userService.createUser(user.getUsername());
        UserDTO createdUserDTO = UserMapping.INSTANCE.toDTO(createdUser);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<User> userOpt = userService.getUserById(id);
        return userOpt.map(user -> ResponseEntity.ok(UserMapping.INSTANCE.toDTO(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, userDTO.getUsername());
        if (updatedUser != null) {
            return ResponseEntity.ok(UserMapping.INSTANCE.toDTO(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
