package com.example.messagingapp.service;

import com.example.messagingapp.entity.User;
import com.example.messagingapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser() {
        User user = new User();
        user.setUsername("testuser");
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
    }

    @Test
    void testRetrieveUser() {
        User user = new User();
        user.setUsername("testuser");
        User savedUser = userRepository.save(user);

        Optional<User> retrievedUser = userRepository.findById(savedUser.getId());
        assertTrue(retrievedUser.isPresent());
        assertEquals("testuser", retrievedUser.get().getUsername());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setUsername("testuser");
        User savedUser = userRepository.save(user);

        savedUser.setUsername("updateduser");
        User updatedUser = userRepository.save(savedUser);

        Optional<User> retrievedUser = userRepository.findById(updatedUser.getId());
        assertTrue(retrievedUser.isPresent());
        assertEquals("updateduser", retrievedUser.get().getUsername());
    }
}
