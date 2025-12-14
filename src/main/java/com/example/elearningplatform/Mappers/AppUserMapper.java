package com.example.elearningplatform.Mappers;

import com.example.elearningplatform.DTO.AppUser.AppUserRequestDTO;
import com.example.elearningplatform.DTO.AppUser.AppUserResponseDTO;
import com.example.elearningplatform.entities.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
       AppUserResponseDTO toAppUserResponseDTO(AppUser appUser);
       AppUser toAppUser(AppUserRequestDTO appUserRequestDTO);
}
