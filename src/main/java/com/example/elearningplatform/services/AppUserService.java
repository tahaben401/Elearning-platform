package com.example.elearningplatform.services;

import com.example.elearningplatform.DTO.AppUserRequestDTO;
import com.example.elearningplatform.DTO.AppUserResponseDTO;
import com.example.elearningplatform.Mappers.AppUserMapper;
import com.example.elearningplatform.Repositories.AppUserRepository;
import com.example.elearningplatform.entities.AppUser;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    public AppUserService(AppUserRepository appUserRepository, AppUserMapper appUserMapper) {

        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }

    public AppUser getUserById(String id){
        return appUserRepository.findById(id).orElse(null);
    }

    public AppUserResponseDTO createUser(AppUserRequestDTO appUserRequestDTO){
          AppUser appUser = appUserMapper.toAppUser(appUserRequestDTO);
          AppUser newUser = appUserRepository.save(appUser);
          return appUserMapper.toAppUserResponseDTO(newUser);
    }

}
