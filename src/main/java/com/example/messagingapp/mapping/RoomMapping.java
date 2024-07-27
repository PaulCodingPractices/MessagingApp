package com.example.messagingapp.mapping;

import com.example.messagingapp.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapping {
    RoomMapping INSTANCE = Mappers.getMapper(RoomMapping.class);

    RoomDTO toDTO(Room room);

    Room toEntity(RoomDTO roomDTO);
}
