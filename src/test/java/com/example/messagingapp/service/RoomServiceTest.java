package com.example.messagingapp.service;

import com.example.messagingapp.entity.AppUser;
import com.example.messagingapp.entity.Room;
import com.example.messagingapp.repository.RoomRepository;
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
class RoomServiceTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void testCreateRoom() {
        Room room = new Room();
        room.setName("Test Room");
        Room savedRoom = roomRepository.save(room);
        assertNotNull(savedRoom.getId());
    }

    @Test
    void testRetrieveRoom() {
        Room room = new Room();
        room.setName("Test Room");
        Room savedRoom = roomRepository.save(room);

        Optional<Room> retrievedRoom = roomRepository.findById(savedRoom.getId());
        assertTrue(retrievedRoom.isPresent());
        assertEquals("Test Room", retrievedRoom.get().getName());
    }

    @Test
    void testAddUserToRoom() {
        Room room = new Room();
        room.setName("Test Room");
        Room savedRoom = roomRepository.save(room);

        AppUser appUser = new AppUser();
        appUser.setUsername("testuser");
        AppUser savedAppUser = appUserRepository.save(appUser);

        savedRoom.addUser(savedAppUser);
        Room updatedRoom = roomRepository.save(savedRoom);

        Optional<Room> retrievedRoom = roomRepository.findById(updatedRoom.getId());
        assertTrue(retrievedRoom.isPresent());
        assertEquals(1, retrievedRoom.get().getUsers().size());
        assertEquals("testuser", retrievedRoom.get().getUsers().iterator().next().getUsername());
    }
}
