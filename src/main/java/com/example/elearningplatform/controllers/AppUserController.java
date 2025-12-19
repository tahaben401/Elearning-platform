//package com.example.elearningplatform.controllers;
//
//import com.example.elearningplatform.DTO.AppUser.signup.AppUserRequestRegisterDTO;
//import com.example.elearningplatform.DTO.AppUser.signup.AppUserResponseDTO;
//import com.example.elearningplatform.services.AuthenticationService;
//import com.example.elearningplatform.services.CoursesService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("users")
//public class AppUserController {
//
//    private final AuthenticationService authenticationService;
//    private final CoursesService coursesService;
//    public AppUserController(AuthenticationService authenticationService, CoursesService coursesService)
//    {
//       this.authenticationService = authenticationService;
//       this.coursesService = coursesService;
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<AppUserResponseDTO> getUserById(@PathVariable String id){
//
//        return new ResponseEntity<AppUserResponseDTO>(
//                authenticationService.getUserById(id),HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<AppUserResponseDTO> createUser(@RequestBody AppUserRequestRegisterDTO appUserRequestRegisterDTO) {
//         return  new ResponseEntity<AppUserResponseDTO>(authenticationService.registerUser(appUserRequestRegisterDTO), HttpStatus.CREATED);
//    }
//
//
//}
