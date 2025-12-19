package com.example.elearningplatform.config.security;

import com.example.elearningplatform.services.auth.AppUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// this is another filter that will be added in the security filter chain
// will be run once per request
@Component
// for logging
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

    public static final String BEARER_ = "Bearer ";
    private JwtUtil jwtUtil;
    private AppUserDetailsService appUserDetailsService;


    public AuthTokenFilter(JwtUtil jwtUtil,AppUserDetailsService appUserDetailsService){
        this.jwtUtil = jwtUtil;
        this.appUserDetailsService = appUserDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
             // we try to get the jwtToken from the request Authorization Header
             // by calling a internal method that is "parseJwt(HttpServletRequest request)"
             String jwtToken = parseJwt(request);

             if(jwtToken != null && jwtUtil.validateJwtToken(jwtToken)) {

                 final String email = jwtUtil.getUserFromToken(jwtToken);
                 // get the userDetails from the user's email
                 // it is The authenticated user's information
                 final UserDetails userDetails = appUserDetailsService.loadUserByUsername(email);

                 // Represents an authenticated user in Spring Security
                 // It's Spring Security's implementation of the Authentication interface
                 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                         // Principal
                         userDetails,
                         // this field concerns the credentials
                         // it is null , because
                         // JWT: Token already proves identity, no need to keep password in memory
                         null,
                         userDetails.getAuthorities()
                         // What can the authenticated user do?
                 );
                 // this adds extra metadata about the authentication request to the token.
                 // like the client's ip address
                 authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                 // Makes authentication info available anywhere in the application
                 // A static holder class that stores the security context for the current thread(current http request)
                 SecurityContextHolder.getContext()
                         .setAuthentication(authenticationToken);

                 // setAuthentication(authenticationToken) marks the user as authenticated

                 // after that , we are sure that the user is now authenticated

                 // the request proceeds to the controller

                 // and the controller can now access the authenticated user
             }

        } catch(Exception e){
            log.error("Cannot Set User Authentication"+ e.getMessage());
        }
        // continue the filter chain
        filterChain.doFilter(request,response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if(headerAuth !=null && headerAuth.startsWith(BEARER_)){
            return headerAuth.substring(BEARER_.length());
            // get the substring without the "Bearer " part
            // to get the actual token
        }
        return null;
    }
}
