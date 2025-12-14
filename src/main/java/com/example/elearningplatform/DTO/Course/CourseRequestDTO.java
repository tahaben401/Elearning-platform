package com.example.elearningplatform.DTO.Course;

import com.example.elearningplatform.entities.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDTO {
    private String courseName;
    private String description;
    private String instructorId;
}
