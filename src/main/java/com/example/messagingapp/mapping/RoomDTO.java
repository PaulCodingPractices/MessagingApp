package com.example.messagingapp.mapping;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class RoomDTO {
    private Long id;

    @NotBlank
    private String name;

    private Set<AppUserDTO> users;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AppUserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUserDTO> users) {
        this.users = users;
    }
}
