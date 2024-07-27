package com.example.messagingapp.service;

import com.example.messagingapp.entity.Message;
import com.example.messagingapp.repository.MessageRepository;
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
class MessageServiceTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void testCreateMessage() {
        Message message = new Message();
        message.setContent("Hello, world!");
        Message savedMessage = messageRepository.save(message);
        assertNotNull(savedMessage.getId());
    }

    @Test
    void testRetrieveMessage() {
        Message message = new Message();
        message.setContent("Hello, world!");
        Message savedMessage = messageRepository.save(message);

        Optional<Message> retrievedMessage = messageRepository.findById(savedMessage.getId());
        assertTrue(retrievedMessage.isPresent());
        assertEquals("Hello, world!", retrievedMessage.get().getContent());
    }

    @Test
    void testDeleteMessage() {
        Message message = new Message();
        message.setContent("Hello, world!");
        Message savedMessage = messageRepository.save(message);

        messageRepository.delete(savedMessage);
        Optional<Message> retrievedMessage = messageRepository.findById(savedMessage.getId());
        assertFalse(retrievedMessage.isPresent());
    }
}
