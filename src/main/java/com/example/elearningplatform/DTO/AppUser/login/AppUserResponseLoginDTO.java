package com.example.elearningplatform.DTO.AppUser.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppUserResponseLoginDTO {
    private String token;
    private String type;
    private String email;
    private String role;
    private String userId;
    private String firstname;
    private String lastname;
}
