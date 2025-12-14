package com.example.elearningplatform.Mappers;

import com.example.elearningplatform.DTO.Course.CourseRequestDTO;
import com.example.elearningplatform.DTO.Course.CourseResponseDTO;
import com.example.elearningplatform.entities.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CoursesMapper {
//      Course toCourse(CourseRequestDTO courseRequestDTO);
      CourseResponseDTO toCourseResponseDTO(Course course);
}
