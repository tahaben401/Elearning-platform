package com.example.elearningplatform.Repositories;

import com.example.elearningplatform.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByEmail(String username);
}
