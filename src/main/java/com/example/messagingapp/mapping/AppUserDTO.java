package com.example.messagingapp.mapping;

import jakarta.validation.constraints.NotBlank;

public class AppUserDTO {
    private Long id;

    @NotBlank
    private String username;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
