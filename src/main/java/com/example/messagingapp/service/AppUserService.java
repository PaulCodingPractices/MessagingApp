package com.example.messagingapp.service;

import com.example.messagingapp.entity.AppUser;
import com.example.messagingapp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser createUser(String username) {
        AppUser appUser = new AppUser(username);
        return appUserRepository.save(appUser);
    }

    public Optional<AppUser> getUserById(Long id) {
        return appUserRepository.findById(id);
    }

    public AppUser updateUser(Long id, String newUsername) {
        Optional<AppUser> userOpt = appUserRepository.findById(id);
        if (userOpt.isPresent()) {
            AppUser appUser = userOpt.get();
            appUser.setUsername(newUsername);
            return appUserRepository.save(appUser);
        }
        return null;
    }
}
