package com.shreyash.blog.payloads;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private String about;
}
