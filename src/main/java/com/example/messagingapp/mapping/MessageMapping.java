package com.example.messagingapp.mapping;

import com.example.messagingapp.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapping {
    MessageMapping INSTANCE = Mappers.getMapper(MessageMapping.class);

    MessageDTO toDTO(Message message);

    Message toEntity(MessageDTO messageDTO);
}
