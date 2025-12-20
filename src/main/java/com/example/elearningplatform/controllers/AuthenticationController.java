package com.example.elearningplatform.controllers;

import com.example.elearningplatform.DTO.AppUser.login.AppUserRequestLoginDTO;
import com.example.elearningplatform.DTO.AppUser.login.AppUserResponseLoginDTO;
import com.example.elearningplatform.DTO.AppUser.signup.AppUserRequestRegisterDTO;
import com.example.elearningplatform.DTO.AppUser.signup.AppUserResponseDTO;
import com.example.elearningplatform.Repositories.AppUserRepository;
import com.example.elearningplatform.config.security.JwtUtil;
import com.example.elearningplatform.entities.AppUser;
import com.example.elearningplatform.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final AppUserRepository appUserRepository;
    private final JwtUtil jwtUtils;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    AuthenticationService authenticationService,
                                    AppUserRepository appUserRepository,
                                    JwtUtil jwtUtils){
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.appUserRepository = appUserRepository;
        this.jwtUtils = jwtUtils;

    }

    @Tag(name="Auth",description = "SignIn/SignUp")
    @Operation(summary = "Sign In",description = "As a user, i want to log in to my account")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody AppUserRequestLoginDTO appUserRequestLoginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            appUserRequestLoginDTO.getEmail(),
                            appUserRequestLoginDTO.getPassword()
                    )
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtUtils.generateToken(userDetails);


            AppUser authenticatedUser = appUserRepository.findByEmail(userDetails.getUsername());
            AppUserResponseLoginDTO response = new AppUserResponseLoginDTO(
                    jwt,
                    "Bearer",
                    authenticatedUser.getEmail(),
                    authenticatedUser.getRole(),
                    authenticatedUser.getId(),
                    authenticatedUser.getFirstname(),
                    authenticatedUser.getLastname()

            );
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid email or password"));
        }

    }

    @Tag(name="Auth",description = "SignIn/SignUp")
    @Operation(summary = "Sign Up",description = "As a user, i want to register, wether as a Student or Instructor")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody AppUserRequestRegisterDTO appUserRequestRegisterDTO){
           if( appUserRepository.existsByEmail(appUserRequestRegisterDTO.getEmail()) ){
               return ResponseEntity.status(HttpStatus.CONFLICT)
                       .body(Map.of("error","User with that email already exists"));
           }

           AppUserResponseDTO createdUser = authenticationService.registerUser(appUserRequestRegisterDTO);
           return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


}
