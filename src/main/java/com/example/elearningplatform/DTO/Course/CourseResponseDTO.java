package com.example.elearningplatform.DTO.Course;

import com.example.elearningplatform.entities.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDTO {
    private String id;
    private String courseName;
    private String description;
    private AppUser instructor;
    private Date created_at;
}
