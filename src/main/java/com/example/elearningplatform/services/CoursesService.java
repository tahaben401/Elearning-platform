package com.example.elearningplatform.services;

import com.example.elearningplatform.DTO.Course.CourseRequestDTO;
import com.example.elearningplatform.DTO.Course.CourseResponseDTO;
import com.example.elearningplatform.Mappers.CoursesMapper;
import com.example.elearningplatform.Repositories.AppUserRepository;
import com.example.elearningplatform.Repositories.CoursesRepository;
import com.example.elearningplatform.entities.AppUser;
import com.example.elearningplatform.entities.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoursesService {
    private final CoursesMapper coursesMapper;
    private CoursesRepository coursesRepository;
    private AppUserRepository appUserRepository;
    public CoursesService(CoursesRepository coursesRepository, CoursesMapper coursesMapper, AppUserRepository appUserRepository) {
        this.coursesRepository = coursesRepository;
        this.coursesMapper = coursesMapper;
        this.appUserRepository = appUserRepository;
    }

    public CourseResponseDTO addCourse(CourseRequestDTO courseRequestDTO) {
           AppUser concernedInstructor = appUserRepository.findById(courseRequestDTO.getInstructorId()).orElse(null);
           Course newCourse = new Course().builder().courseName(courseRequestDTO.getCourseName())
                   .description(courseRequestDTO.getDescription())
                   .instructor(concernedInstructor)
                   .build();
           return coursesMapper.toCourseResponseDTO(coursesRepository.save(newCourse));
    }

    public List<CourseResponseDTO> getCourses() {
        return coursesRepository.findAll().stream().map(course -> coursesMapper.toCourseResponseDTO(course)).collect(Collectors.toList());
    }

    public List<CourseResponseDTO> getInstructorCourses(String instructorId) {
        AppUser instructor = appUserRepository.findById(instructorId).orElse(null);
        List<CourseResponseDTO> instructorCourses = instructor.getCourses().stream().map(course -> coursesMapper.toCourseResponseDTO(course)).collect(Collectors.toList());
        return instructorCourses;
    }
}
