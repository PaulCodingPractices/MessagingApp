package com.example.messagingapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Import(TestcontainersConfiguration.class)
@SpringBootTest
class MessagingAppApplicationTests {

    @Test
    void contextLoads() {
    }

}
