package com.example.messagingapp.API;

import com.example.messagingapp.entity.Message;
import com.example.messagingapp.repository.MessageRepository;
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
class MessageApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void testCreateMessage() {
        Message message = new Message();
        message.setContent("Hello, world!");

        ResponseEntity<Message> response = restTemplate.postForEntity("/messages", message, Message.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Message savedMessage = messageRepository.findById(response.getBody().getId()).orElse(null);
        assertNotNull(savedMessage);
        assertEquals("Hello, world!", savedMessage.getContent());
    }

    @Test
    void testRetrieveMessage() {
        Message message = new Message();
        message.setContent("Hello, world!");
        Message savedMessage = messageRepository.save(message);

        ResponseEntity<Message> response = restTemplate.getForEntity("/messages/" + savedMessage.getId(), Message.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hello, world!", response.getBody().getContent());
    }

    @Test
    void testDeleteMessage() {
        Message message = new Message();
        message.setContent("Hello, world!");
        Message savedMessage = messageRepository.save(message);

        restTemplate.delete("/messages/" + savedMessage.getId());

        Message deletedMessage = messageRepository.findById(savedMessage.getId()).orElse(null);
        assertNull(deletedMessage);
    }
}
