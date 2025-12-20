package com.example.elearningplatform.DTO.Enrollment;

import com.example.elearningplatform.entities.AppUser;
import com.example.elearningplatform.entities.Course;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponseDTO {
    private String id;

    private Course course;

//    private AppUser student;

    private Date created_at;

    private Date updated_at;
}
