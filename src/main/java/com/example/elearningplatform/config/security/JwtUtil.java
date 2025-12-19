package com.example.elearningplatform.config.security;

import com.example.elearningplatform.entities.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExpiration;

    private SecretKey secretKey;

    @PostConstruct
    // will be called after instanciation of the class
    public void init(){
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
    // to generate a jwt token for a user's email
    public String generateToken(AppUser user){
        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(secretKey)
                .compact();
    }
    // get the email of the user from the token
    public String getUserFromToken(String token){
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateJwtToken(String token){
        try {

            Jwts.parser().verifyWith(secretKey).build()
                    .parseSignedClaims(token);
            return true;
            // we are here able to verify with our secret key the token
            // if true is returned then the jwt token is valid
        } catch(Exception e) {
            log.error("JWT validation error : "+e.getMessage());
        }
        return false;
    }
}
