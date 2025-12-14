package com.example.elearningplatform.Mappers;

import com.example.elearningplatform.DTO.AppUserRequestDTO;
import com.example.elearningplatform.DTO.AppUserResponseDTO;
import com.example.elearningplatform.entities.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
       AppUserResponseDTO toAppUserResponseDTO(AppUser appUser);
       AppUser toAppUser(AppUserRequestDTO appUserRequestDTO);
}
