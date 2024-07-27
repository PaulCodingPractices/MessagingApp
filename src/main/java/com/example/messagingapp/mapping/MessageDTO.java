package com.example.messagingapp.mapping;

import jakarta.validation.constraints.NotBlank;

public class MessageDTO {
    private Long id;

    @NotBlank
    private String content;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
