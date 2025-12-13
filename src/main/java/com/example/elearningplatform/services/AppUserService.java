package com.example.elearningplatform.services;

import com.example.elearningplatform.DTO.AppUserRequestDTO;
import com.example.elearningplatform.DTO.AppUserResponseDTO;
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

    public AppUserResponseDTO createUser(AppUserRequestDTO appUserRequestDTO){
        //TODO
        // get content of request dto
        // save the user calling the repository
        // get the new created user
        // map it to response dto
        // and return it to the controller
        return new AppUserResponseDTO();
    }

}
