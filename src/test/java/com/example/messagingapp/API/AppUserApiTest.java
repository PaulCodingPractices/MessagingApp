package com.example.messagingapp.API;

import com.example.messagingapp.entity.User;
import com.example.messagingapp.repository.UserRepository;
import com.example.messagingapp.mapping.UserDTO;
import com.example.messagingapp.mapping.UserMapping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@ActiveProfiles("test")
class UserApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");

        ResponseEntity<UserDTO> response = restTemplate.postForEntity("/users", userDTO, UserDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        User savedUser = userRepository.findById(response.getBody().getId()).orElse(null);
        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
    }

    @Test
    void testRetrieveUser() {
        User user = new User();
        user.setUsername("testuser");
        User savedUser = userRepository.save(user);

        ResponseEntity<UserDTO> response = restTemplate.getForEntity("/users/" + savedUser.getId(), UserDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        UserDTO responseBody = response.getBody();
        assertNotNull(responseBody, "Response body is null");
        assertEquals("testuser", responseBody.getUsername());
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setUsername("testuser");
        User savedUser = userRepository.save(user);

        UserDTO updatedUserDTO = new UserDTO();
        updatedUserDTO.setUsername("updateduser");

        restTemplate.put("/users/" + savedUser.getId(), updatedUserDTO);

        User retrievedUser = userRepository.findById(savedUser.getId()).orElse(null);
        assertNotNull(retrievedUser);
        assertEquals("updateduser", retrievedUser.getUsername());
    }
}
