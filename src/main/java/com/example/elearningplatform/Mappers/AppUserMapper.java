package com.example.elearningplatform.Mappers;

import com.example.elearningplatform.DTO.AppUser.signup.AppUserRequestRegisterDTO;
import com.example.elearningplatform.DTO.AppUser.signup.AppUserResponseDTO;
import com.example.elearningplatform.entities.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
       AppUserResponseDTO toAppUserResponseDTO(AppUser appUser);
       AppUser toAppUser(AppUserRequestRegisterDTO appUserRequestRegisterDTO);
}
