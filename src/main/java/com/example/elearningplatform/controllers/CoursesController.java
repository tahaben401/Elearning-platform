package com.example.elearningplatform.controllers;

import com.example.elearningplatform.DTO.Course.CourseRequestDTO;
import com.example.elearningplatform.DTO.Course.CourseResponseDTO;
import com.example.elearningplatform.DTO.Enrollment.EnrollmentRequestDTO;
import com.example.elearningplatform.DTO.Enrollment.EnrollmentResponseDTO;
import com.example.elearningplatform.entities.Course;
import com.example.elearningplatform.services.CoursesService;
import com.example.elearningplatform.services.EnrollmentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("courses")
public class CoursesController {
    private final EnrollmentsService enrollmentsService;
    private final CoursesService coursesService;
    public CoursesController(EnrollmentsService enrollmentsService, CoursesService coursesService) {
        this.coursesService = coursesService;
        this.enrollmentsService = enrollmentsService;
    }
    @GetMapping
    public List<CourseResponseDTO> getCourses(){
//        return "Courses";
        return coursesService.getCourses();
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseRequestDTO){
        return new ResponseEntity<CourseResponseDTO>(coursesService.addCourse(courseRequestDTO), HttpStatus.CREATED);
    }
    @PostMapping("enroll")
    public ResponseEntity<EnrollmentResponseDTO> enrollToCourse(@RequestBody EnrollmentRequestDTO enrollmentRequestDTO){
         return new ResponseEntity<EnrollmentResponseDTO>(enrollmentsService.enrollToCourse(enrollmentRequestDTO),HttpStatus.CREATED);
    }

    @GetMapping("enroll/{userId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> studentEnrolledCourses(@PathVariable String userId){
        return new ResponseEntity<List<EnrollmentResponseDTO>>(enrollmentsService.getStudentEnrolledCourses(userId),HttpStatus.OK);
    }
}
