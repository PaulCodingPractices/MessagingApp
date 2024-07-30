package com.example.messagingapp;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagingAppApplication.class, args);
    }

    @PostConstruct
    public void init() {
    System.out.println("SECURE_USER_PASSWORD: " + System.getenv("SECURE_USER_PASSWORD"));
}


}
