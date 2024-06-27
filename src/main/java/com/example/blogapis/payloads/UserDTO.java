package com.example.blogapis.payloads;

import com.example.blogapis.utils.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {


    private int id;
    @NotEmpty
    @Size(min = 4,message = "UserName must be minimum greater than 4 characters")
    private String username;
    @Email(message = "email id is not valid",regexp = Constants.EMAIL_ID_REGEXP)
    private String email;
    @Size(min=8 ,max = 26,message = "Password must be min of 8 characters and max 26 characters")
    private String password;
    @NotEmpty
    private String about;
}
