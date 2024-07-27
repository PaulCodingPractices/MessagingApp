package com.example.messagingapp.service;

import com.example.messagingapp.entity.AppUser;
import com.example.messagingapp.repository.AppUserRepository;
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
class AppAppUserServiceTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void testCreateUser() {
        AppUser appUser = new AppUser();
        appUser.setUsername("testuser");
        AppUser savedAppUser = appUserRepository.save(appUser);
        assertNotNull(savedAppUser.getId());
    }

    @Test
    void testRetrieveUser() {
        AppUser appUser = new AppUser();
        appUser.setUsername("testuser");
        AppUser savedAppUser = appUserRepository.save(appUser);

        Optional<AppUser> retrievedUser = appUserRepository.findById(savedAppUser.getId());
        assertTrue(retrievedUser.isPresent());
        assertEquals("testuser", retrievedUser.get().getUsername());
    }

    @Test
    void testUpdateUser() {
        AppUser appUser = new AppUser();
        appUser.setUsername("testuser");
        AppUser savedAppUser = appUserRepository.save(appUser);

        savedAppUser.setUsername("updateduser");
        AppUser updatedAppUser = appUserRepository.save(savedAppUser);

        Optional<AppUser> retrievedUser = appUserRepository.findById(updatedAppUser.getId());
        assertTrue(retrievedUser.isPresent());
        assertEquals("updateduser", retrievedUser.get().getUsername());
    }
}
