package com.example.elearningplatform.services;

import com.example.elearningplatform.Repositories.AppUserRepository;
import com.example.elearningplatform.entities.AppUser;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser getUserById(String id){
        return appUserRepository.findById(id).orElse(null);
    }

}
