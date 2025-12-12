package com.example.elearningplatform.Repositories;

import com.example.elearningplatform.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Course,String> {
}
