package com.example.elearningplatform.Mappers;

import com.example.elearningplatform.DTO.AppUserResponseDTO;
import com.example.elearningplatform.entities.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppUserMapper {
       AppUserResponseDTO toAppUserResponseDTO(AppUser appUser);
}
