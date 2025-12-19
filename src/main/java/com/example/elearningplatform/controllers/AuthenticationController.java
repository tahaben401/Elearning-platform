package com.example.elearningplatform.controllers;

import com.example.elearningplatform.DTO.AppUser.login.AppUserRequestLoginDTO;
import com.example.elearningplatform.DTO.AppUser.signup.AppUserRequestRegisterDTO;
import com.example.elearningplatform.DTO.AppUser.signup.AppUserResponseDTO;
import com.example.elearningplatform.Repositories.AppUserRepository;
import com.example.elearningplatform.config.security.JwtUtil;
import com.example.elearningplatform.services.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private AuthenticationService authenticationService;
    private AppUserRepository appUserRepository;
    private JwtUtil jwtUtils;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    AuthenticationService authenticationService,
                                    AppUserRepository appUserRepository,
                                    JwtUtil jwtUtils){
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.appUserRepository = appUserRepository;
        this.jwtUtils = jwtUtils;

    }

    @PostMapping("/signin")
    public String authenticateUser(@RequestBody AppUserRequestLoginDTO appUserRequestLoginDTO){
         Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(
                 appUserRequestLoginDTO.getEmail(),
                 appUserRequestLoginDTO.getPassword()
                 )
         );
         UserDetails userDetails =(UserDetails) authentication.getPrincipal();
         return jwtUtils.generateToken(userDetails.getUsername());
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody AppUserRequestRegisterDTO appUserRequestRegisterDTO){
           if( appUserRepository.existsByEmail(appUserRequestRegisterDTO.getEmail()) ){
               return "User already exists";
           }

           AppUserResponseDTO createdUser = authenticationService.registerUser(appUserRequestRegisterDTO);
           return "User registered successfully";
    }


}
