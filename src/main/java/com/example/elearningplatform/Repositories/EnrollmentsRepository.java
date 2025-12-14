package com.example.elearningplatform.Repositories;

import com.example.elearningplatform.entities.Course;
import com.example.elearningplatform.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentsRepository extends JpaRepository<Enrollment,String> {
//    Optional<Enrollment> findEnrollmentByUserId(String userId);
}
