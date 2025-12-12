package com.example.elearningplatform.services;

import com.example.elearningplatform.Repositories.CoursesRepository;
import com.example.elearningplatform.Repositories.EnrollmentsRepository;
import com.example.elearningplatform.entities.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentsService {
     private final EnrollmentsRepository enrollmentsRepository;
     private final CoursesRepository coursesRepository;
     public EnrollmentsService(EnrollmentsRepository enrollmentsRepository, CoursesRepository coursesRepository) {
         this.enrollmentsRepository = enrollmentsRepository;
         this.coursesRepository = coursesRepository;
     }

     public List<Optional<Course>> getUserCourses(String userId){
         List<Optional<Course>> userCourses = new ArrayList<Optional<Course>>();
         enrollmentsRepository.findEnrollmentByUserId(userId).ifPresent(enrollment -> userCourses.add(coursesRepository.findById(enrollment.getCourse().getId())));
         return userCourses;
     }
}
