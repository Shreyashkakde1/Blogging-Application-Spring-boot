package com.shreyash.blog.payloads;

import com.shreyash.blog.entities.Category;
import com.shreyash.blog.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class PostDto {

    private String title;
    private String content;
    private String imageName = "default.png";
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
}
