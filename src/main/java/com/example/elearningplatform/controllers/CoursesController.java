package com.example.elearningplatform.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("courses")
public class CoursesController {
    @GetMapping
    public String getCourses(){
        return "Courses";
    }
}
