package com.example.elearningplatform.services;

import com.example.elearningplatform.DTO.Course.CourseRequestDTO;
import com.example.elearningplatform.DTO.Course.CourseResponseDTO;
import com.example.elearningplatform.Mappers.CoursesMapper;
import com.example.elearningplatform.Repositories.AppUserRepository;
import com.example.elearningplatform.Repositories.CoursesRepository;
import com.example.elearningplatform.entities.AppUser;
import com.example.elearningplatform.entities.Course;
import org.jspecify.annotations.Nullable;
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

    public CourseResponseDTO addCourse(CourseRequestDTO courseRequestDTO,String instructorEmail) throws Exception{
        if(appUserRepository.existsByEmail(instructorEmail)) {
            AppUser concernedInstructor = appUserRepository.findByEmail(instructorEmail);
            Course newCourse = new Course().builder().courseName(courseRequestDTO.getCourseName())
                    .description(courseRequestDTO.getDescription())
                    .instructor(concernedInstructor)
                    .build();
            return coursesMapper.toCourseResponseDTO(coursesRepository.save(newCourse));
        }
        throw new Exception("User doesnt exist");
    }

    public List<CourseResponseDTO> getCourses() {
        return coursesRepository.findAll().stream().map(course -> coursesMapper.toCourseResponseDTO(course)).collect(Collectors.toList());
    }

    public List<CourseResponseDTO> getInstructorCourses(String instructorId) {
        AppUser instructor = appUserRepository.findById(instructorId).orElse(null);
        List<CourseResponseDTO> instructorCourses = instructor.getCourses().stream().map(course -> coursesMapper.toCourseResponseDTO(course)).collect(Collectors.toList());
        return instructorCourses;
    }

    public  List<CourseResponseDTO> getMyCourses(String instructorEmail) throws Exception {
        if(appUserRepository.existsByEmail(instructorEmail)) {
            AppUser authenticatedInstructor = appUserRepository.findByEmail(instructorEmail);
            List<CourseResponseDTO> instructorCourses = authenticatedInstructor.getCourses().stream().map(course -> coursesMapper.toCourseResponseDTO(course)).collect(Collectors.toList());
            return instructorCourses;
        }
        throw new Exception("User doesnt exist");
    }
}
