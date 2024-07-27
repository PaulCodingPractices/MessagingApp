package com.example.messagingapp.mapping;

import com.example.messagingapp.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppUserMapping {
    AppUserMapping INSTANCE = Mappers.getMapper(AppUserMapping.class);

    AppUserDTO toDTO(AppUser appUser);

    AppUser toEntity(AppUserDTO appUserDTO);
}
