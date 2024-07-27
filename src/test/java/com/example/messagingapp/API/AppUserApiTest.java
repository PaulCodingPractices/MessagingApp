package com.example.messagingapp.API;

import com.example.messagingapp.mapping.AppUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.MultiValueMap;

import java.nio.charset.Charset;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AppUserApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
            setContentType(MediaType.APPLICATION_JSON);
        }};
    }

    @Test
    public void testCreateUser() {
        AppUserDTO userDTO = new AppUserDTO();
        userDTO.setUsername("newuser");

        HttpEntity<AppUserDTO> request = new HttpEntity<>(userDTO, createHeaders("testuser", "testpassword"));

        ResponseEntity<AppUserDTO> response = restTemplate.postForEntity("/users", request, AppUserDTO.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Additional check to ensure the body is not null and contains the expected username
        AppUserDTO createdUser = response.getBody();
        assertNotNull(createdUser, "Created user should not be null");
        assertEquals("newuser", createdUser.getUsername(), "Username should be 'newuser'");
    }

    @Test
    public void testRetrieveUser() {
        HttpEntity<Void> request = new HttpEntity<>(createHeaders("testuser", "testpassword"));

        ResponseEntity<AppUserDTO> response = restTemplate.exchange("/users/1", HttpMethod.GET, request, AppUserDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Additional check to ensure the body is not null and contains the expected username
        AppUserDTO retrievedUser = response.getBody();
        assertNotNull(retrievedUser, "Retrieved user should not be null");
    }

    @Test
    public void testUpdateUser() {
        AppUserDTO userDTO = new AppUserDTO();
        userDTO.setUsername("updateduser");

        HttpEntity<AppUserDTO> request = new HttpEntity<>(userDTO, createHeaders("testuser", "testpassword"));

        ResponseEntity<Void> updateResponse = restTemplate.exchange("/users/1", HttpMethod.PUT, request, Void.class);
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode(), "Update response status should be OK");

        ResponseEntity<AppUserDTO> getResponse = restTemplate.exchange("/users/1", HttpMethod.GET, new HttpEntity<>(createHeaders("testuser", "testpassword")), AppUserDTO.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode(), "Get response status should be OK");

        AppUserDTO updatedUserDTO = getResponse.getBody();
        assertNotNull(updatedUserDTO, "Updated user DTO should not be null");
        assertEquals("updateduser", updatedUserDTO.getUsername(), "Username should be updated to 'updateduser'");
    }
}
