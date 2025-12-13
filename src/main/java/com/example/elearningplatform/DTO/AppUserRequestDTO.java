package com.example.elearningplatform.DTO;

import lombok.Builder;

@Builder
public class AppUserRequestDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
}
