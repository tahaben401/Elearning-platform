package com.example.elearningplatform.controllers;

import com.example.elearningplatform.DTO.AppUser.AppUserRequestDTO;
import com.example.elearningplatform.DTO.AppUser.AppUserResponseDTO;
import com.example.elearningplatform.DTO.Course.CourseRequestDTO;
import com.example.elearningplatform.DTO.Course.CourseResponseDTO;
import com.example.elearningplatform.services.AppUserService;
import com.example.elearningplatform.services.CoursesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class AppUserController {

    private final AppUserService appUserService;
    private final CoursesService coursesService;
    public AppUserController(AppUserService appUserService, CoursesService coursesService)
    {
       this.appUserService = appUserService;
       this.coursesService = coursesService;
    }

    @GetMapping("{id}")
    public ResponseEntity<AppUserResponseDTO> getUserById(@PathVariable String id){

        return new ResponseEntity<AppUserResponseDTO>(
                appUserService.getUserById(id),HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<AppUserResponseDTO> createUser(@RequestBody AppUserRequestDTO appUserRequestDTO) {
         return  new ResponseEntity<AppUserResponseDTO>(appUserService.createUser(appUserRequestDTO), HttpStatus.CREATED);
    }


}
