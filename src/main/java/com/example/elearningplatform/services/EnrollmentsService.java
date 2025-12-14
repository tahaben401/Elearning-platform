package com.example.elearningplatform.services;

import com.example.elearningplatform.DTO.Enrollment.EnrollmentRequestDTO;
import com.example.elearningplatform.DTO.Enrollment.EnrollmentResponseDTO;
import com.example.elearningplatform.Mappers.EnrollmentMapper;
import com.example.elearningplatform.Repositories.AppUserRepository;
import com.example.elearningplatform.Repositories.CoursesRepository;
import com.example.elearningplatform.Repositories.EnrollmentsRepository;
import com.example.elearningplatform.entities.AppUser;
import com.example.elearningplatform.entities.Course;
import com.example.elearningplatform.entities.Enrollment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentsService {
     private final EnrollmentsRepository enrollmentsRepository;
     private final CoursesRepository coursesRepository;
     private final AppUserRepository appUserRepository;
     private final EnrollmentMapper enrollmentMapper;
     public EnrollmentsService(EnrollmentsRepository enrollmentsRepository, CoursesRepository coursesRepository, AppUserRepository appUserRepository,EnrollmentMapper enrollmentMapper) {
         this.enrollmentsRepository = enrollmentsRepository;
         this.coursesRepository = coursesRepository;
         this.appUserRepository = appUserRepository;
         this.enrollmentMapper = enrollmentMapper;
     }


    public EnrollmentResponseDTO enrollToCourse(EnrollmentRequestDTO enrollmentRequestDTO) {
         AppUser concernedStudent = appUserRepository.findById(enrollmentRequestDTO.getStudentId()).orElse(null);
         Course concernedCourse = coursesRepository.findById(enrollmentRequestDTO.getCourseId()).orElse(null);
         Enrollment enrollment = new Enrollment().builder()
                         .course(concernedCourse).
                         student(concernedStudent).
                         build();
         return enrollmentMapper.toEnrollmentResponseDTO(enrollmentsRepository.save(enrollment));

    }

    public List<EnrollmentResponseDTO> getStudentEnrolledCourses(String userId) {
         AppUser appUser  = appUserRepository.findById(userId).orElse(null);
         List<EnrollmentResponseDTO> userEnrollments =  enrollmentsRepository.findByStudent(appUser).stream().map(
                 enrollment -> enrollmentMapper.toEnrollmentResponseDTO(enrollment)
         ).collect(Collectors.toList());
         return userEnrollments;
    }
}
