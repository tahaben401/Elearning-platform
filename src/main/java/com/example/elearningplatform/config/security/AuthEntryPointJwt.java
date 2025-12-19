package com.example.elearningplatform.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    // AuthenticationEntryPoint is an interface
    // that handles what happens
    // when an unauthenticated user tries to access a protected resource.
    // in this implementation , we will simply return a 401 unauthorized
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {


        // Servlet is a Java class that handles HTTP requests and responses on the server side
        // HttpServletResponse : Low Level
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"401 Unauthorized");

    }
}
