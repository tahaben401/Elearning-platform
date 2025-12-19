package com.example.elearningplatform.DTO.AppUser.signup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserRequestRegisterDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;

}
