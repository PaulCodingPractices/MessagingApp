package com.example.messagingapp.controller;

import com.example.messagingapp.entity.Room;
import com.example.messagingapp.mapping.RoomMapping;
import com.example.messagingapp.mapping.RoomDTO;
import com.example.messagingapp.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@RequestBody @Valid RoomDTO roomDTO) {
        Room room = RoomMapping.INSTANCE.toEntity(roomDTO);
        Room createdRoom = roomService.createRoom(room.getName());
        RoomDTO createdRoomDTO = RoomMapping.INSTANCE.toDTO(createdRoom);
        return new ResponseEntity<>(createdRoomDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        Optional<Room> roomOpt = roomService.getRoomById(id);
        return roomOpt.map(room -> ResponseEntity.ok(RoomMapping.INSTANCE.toDTO(room)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{roomId}/users/{userId}")
    public ResponseEntity<RoomDTO> addUserToRoom(@PathVariable Long roomId, @PathVariable Long userId) {
        Room updatedRoom = roomService.addUserToRoom(roomId, userId);
        if (updatedRoom != null) {
            return ResponseEntity.ok(RoomMapping.INSTANCE.toDTO(updatedRoom));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("Validation error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

