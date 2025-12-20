package com.example.elearningplatform.controllers;

import com.example.elearningplatform.DTO.Course.CourseRequestDTO;
import com.example.elearningplatform.DTO.Course.CourseResponseDTO;
import com.example.elearningplatform.DTO.Enrollment.EnrollmentRequestDTO;
import com.example.elearningplatform.DTO.Enrollment.EnrollmentResponseDTO;
import com.example.elearningplatform.services.CoursesService;
import com.example.elearningplatform.services.EnrollmentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("courses")
public class CoursesController {
    private final EnrollmentsService enrollmentsService;
    private final CoursesService coursesService;
    public CoursesController(EnrollmentsService enrollmentsService, CoursesService coursesService) {
        this.coursesService = coursesService;
        this.enrollmentsService = enrollmentsService;
    }

    @Tag(name="Get",description = "Get Courses")
    @Operation(summary = "Get all Courses",description = "Retrieve all courses whether you are student or instructor")
    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return ResponseEntity.ok(coursesService.getCourses());
    }

    @Tag(name="Get",description = "Get Courses")
    @Operation(summary = "Get instructor's courses",description = "Search for courses done by a specific instructor")
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<CourseResponseDTO>> getCoursesByInstructor(
            @PathVariable String instructorId) {
        return ResponseEntity.ok(coursesService.getInstructorCourses(instructorId));
    }

    @SecurityRequirement(name = "Authorization")
    @Tag(name="Get",description = "Get Courses")
    @Operation(summary = "Get my courses",description = "As an instructor , i want to see all the courses i created")
    @GetMapping("/my-courses")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<List<CourseResponseDTO>> getMyCourses(
            @AuthenticationPrincipal UserDetails userDetails) throws Exception {

        try {
            String instructorEmail = userDetails.getUsername();
            return ResponseEntity.ok(coursesService.getMyCourses(instructorEmail));
        } catch(Exception e){
            throw new Exception("Error occured : "+e.getMessage());
        }
    }

    @SecurityRequirement(name = "Authorization")
    @Tag(name="Post",description = "publish/enroll to Course")
    @Operation(summary = "Publish a Course",description = "As an instuctor, I want to publish a course")
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

    @SecurityRequirement(name = "Authorization")
    @Tag(name="Post",description = "publish/enroll to Course")
    @Operation(summary = "Enroll to a Course",description = "As a student i want to enroll to a specific course")
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


    @SecurityRequirement(name = "Authorization")
    @Tag(name="Get",description = "Get Courses")
    @Operation(summary = "Get my enrollments",description = "As a student , I want to see the courses that I enrolled to")
    @GetMapping("/my-enrollments")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<EnrollmentResponseDTO>> studentEnrolledCourses(@AuthenticationPrincipal UserDetails userDetails) throws Exception {
        try {
            String studentEmail = userDetails.getUsername();
            List<EnrollmentResponseDTO> studentEnrollments = enrollmentsService.getStudentEnrolledCourses(studentEmail);
            return ResponseEntity.status(HttpStatus.OK).body(studentEnrollments);
        } catch(Exception e){
            throw new Exception("ERROR occured : "+e.getMessage());
        }
    }
}
