package com.example.elearningplatform.controllers;

import com.example.elearningplatform.Repositories.CoursesRepository;
import com.example.elearningplatform.entities.Course;
import com.example.elearningplatform.services.EnrollmentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("courses")
public class CoursesController {
    private final EnrollmentsService enrollmentsService;
    public CoursesController(EnrollmentsService enrollmentsService) {
        this.enrollmentsService = enrollmentsService;
    }
    @GetMapping
    public String getCourses(){
        return "Courses";
    }

    public List<Optional<Course>> getUserCourses(String userId){
          return enrollmentsService.getUserCourses(userId);
    }
}
