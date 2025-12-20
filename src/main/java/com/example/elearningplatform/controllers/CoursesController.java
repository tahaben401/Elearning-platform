package com.example.elearningplatform.controllers;

import com.example.elearningplatform.DTO.Course.CourseRequestDTO;
import com.example.elearningplatform.DTO.Course.CourseResponseDTO;
import com.example.elearningplatform.DTO.Enrollment.EnrollmentRequestDTO;
import com.example.elearningplatform.DTO.Enrollment.EnrollmentResponseDTO;
import com.example.elearningplatform.services.CoursesService;
import com.example.elearningplatform.services.EnrollmentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public List<CourseResponseDTO> getCourses(@RequestParam(required = false) String instructorId){
        if(instructorId == null){
            return coursesService.getCourses();
        }
        return coursesService.getInstructorCourses(instructorId);
    }

    @PostMapping
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseRequestDTO,
                                                          @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        try{
            String instructorEmail = userDetails.getUsername();
            CourseResponseDTO course = coursesService.addCourse(courseRequestDTO,instructorEmail);
            return ResponseEntity.status(HttpStatus.CREATED).body(course);
        } catch(Exception e){
            throw new Exception("ERROR :"+e.getMessage() );
        }

    }
    @PostMapping("/enroll")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<EnrollmentResponseDTO> enrollToCourse(@RequestBody EnrollmentRequestDTO enrollmentRequestDTO,@AuthenticationPrincipal UserDetails userDetails) throws Exception {

        try {
            String studentEmail = userDetails.getUsername();
            EnrollmentResponseDTO enrollment = enrollmentsService.enrollToCourse(
                    enrollmentRequestDTO,
                    studentEmail
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @GetMapping("/my-enrollments")
//    @PreAuthorize("hasRole('STUDENT')")
//    public ResponseEntity<List<EnrollmentResponseDTO>> studentEnrolledCourses(@AuthenticationPrincipal UserDetails userDetails){
//        return new ResponseEntity<List<EnrollmentResponseDTO>>(enrollmentsService.getStudentEnrolledCourses(userId),HttpStatus.OK);
//    }
}
