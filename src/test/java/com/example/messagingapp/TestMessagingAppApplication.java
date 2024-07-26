package com.example.messagingapp;

import org.springframework.boot.SpringApplication;

public class TestMessagingAppApplication {

    public static void main(String[] args) {
        SpringApplication.from(MessagingAppApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
