package com.example.messagingapp.controller;

import com.example.messagingapp.entity.Message;
import com.example.messagingapp.mapping.MessageDTO;
import com.example.messagingapp.mapping.MessageMapping;
import com.example.messagingapp.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageDTO> createMessage(@RequestBody @Valid MessageDTO messageDTO) {
        Message message = MessageMapping.INSTANCE.toEntity(messageDTO);
        Message createdMessage = messageService.createMessage(message.getContent());
        MessageDTO createdMessageDTO = MessageMapping.INSTANCE.toDTO(createdMessage);
        return new ResponseEntity<>(createdMessageDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO> getMessageById(@PathVariable Long id) {
        Optional<Message> messageOpt = messageService.getMessageById(id);
        return messageOpt.map(message -> ResponseEntity.ok(MessageMapping.INSTANCE.toDTO(message)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("Validation error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
