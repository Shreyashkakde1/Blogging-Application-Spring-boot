package com.shreyash.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 60,min = 2,  message = "Name must not exceed 60 characters")
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @Size(max = 255,min = 4,  message = "About must not exceed 255 characters")
    private String about;
}
