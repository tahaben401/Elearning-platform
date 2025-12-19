package com.example.elearningplatform.services;

import com.example.elearningplatform.DTO.AppUser.signup.AppUserRequestRegisterDTO;
import com.example.elearningplatform.DTO.AppUser.signup.AppUserResponseDTO;
import com.example.elearningplatform.Mappers.AppUserMapper;
import com.example.elearningplatform.Repositories.AppUserRepository;
import com.example.elearningplatform.entities.AppUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;
    private final AppUserMapper appUserMapper;
    public AuthenticationService(AppUserRepository appUserRepository,PasswordEncoder passwordEncoder, AppUserMapper appUserMapper) {

        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }

    public AppUserResponseDTO getUserById(String id){

        AppUser appUser = appUserRepository.findById(id).orElse(null);
        return appUserMapper.toAppUserResponseDTO(appUser);
    }

    public AppUserResponseDTO registerUser(AppUserRequestRegisterDTO appUserRequestRegisterDTO){
          AppUser appUser = appUserMapper.toAppUser(appUserRequestRegisterDTO);
          appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
          AppUser newUser = appUserRepository.save(appUser);
          return appUserMapper.toAppUserResponseDTO(newUser);
    }

}
