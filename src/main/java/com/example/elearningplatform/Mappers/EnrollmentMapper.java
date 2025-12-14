package com.example.elearningplatform.Mappers;

import com.example.elearningplatform.DTO.Enrollment.EnrollmentResponseDTO;
import com.example.elearningplatform.entities.Enrollment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
      EnrollmentResponseDTO toEnrollmentResponseDTO(Enrollment enrollment);
}
