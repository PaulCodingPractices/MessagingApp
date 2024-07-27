package com.example.messagingapp.controller;

import com.example.messagingapp.entity.AppUser;
import com.example.messagingapp.mapping.AppUserDTO;
import com.example.messagingapp.mapping.AppUserMapping;
import com.example.messagingapp.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping
    public ResponseEntity<AppUserDTO> createUser(@RequestBody @Valid AppUserDTO appUserDTO) {
        AppUser appUser = AppUserMapping.INSTANCE.toEntity(appUserDTO);
        AppUser createdAppUser = appUserService.createUser(appUser.getUsername());
        AppUserDTO createdAppUserDTO = AppUserMapping.INSTANCE.toDTO(createdAppUser);
        return new ResponseEntity<>(createdAppUserDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Long id) {
        Optional<AppUser> userOpt = appUserService.getUserById(id);
        return userOpt.map(user -> ResponseEntity.ok(AppUserMapping.INSTANCE.toDTO(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserDTO> updateUser(@PathVariable Long id, @RequestBody AppUserDTO appUserDTO) {
        AppUser updatedAppUser = appUserService.updateUser(id, appUserDTO.getUsername());
        if (updatedAppUser != null) {
            return ResponseEntity.ok(AppUserMapping.INSTANCE.toDTO(updatedAppUser));
        } else {
            return ResponseEntity.notFound().build();
        }
}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("Validation error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

