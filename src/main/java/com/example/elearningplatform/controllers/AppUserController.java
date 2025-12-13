package com.example.elearningplatform.controllers;

import com.example.elearningplatform.DTO.AppUserRequestDTO;
import com.example.elearningplatform.DTO.AppUserResponseDTO;
import com.example.elearningplatform.entities.AppUser;
import com.example.elearningplatform.services.AppUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
       this.appUserService = appUserService;
    }

    @GetMapping
    public AppUser getUserById(String id){
        return appUserService.getUserById(id);
    }
    
    @PostMapping
    public AppUserResponseDTO createUser(@RequestBody AppUserRequestDTO appUserRequestDTO) {

    }
}
