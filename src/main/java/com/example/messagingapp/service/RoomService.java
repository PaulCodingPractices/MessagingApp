package com.example.messagingapp.service;

import com.example.messagingapp.entity.Room;
import com.example.messagingapp.entity.AppUser;
import com.example.messagingapp.repository.RoomRepository;
import com.example.messagingapp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public Room createRoom(String name) {
        Room room = new Room(name);
        return roomRepository.save(room);
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Room addUserToRoom(Long roomId, Long userId) {
        Optional<Room> roomOpt = roomRepository.findById(roomId);
        Optional<AppUser> userOpt = appUserRepository.findById(userId);

        if (roomOpt.isPresent() && userOpt.isPresent()) {
            Room room = roomOpt.get();
            AppUser appUser = userOpt.get();
            room.addUser(appUser);
            return roomRepository.save(room);
        }
        return null;
    }
}
