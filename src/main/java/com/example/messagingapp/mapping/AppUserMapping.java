package com.example.messagingapp.mapping;

import com.example.messagingapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapping {
    UserMapping INSTANCE = Mappers.getMapper(UserMapping.class);

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
