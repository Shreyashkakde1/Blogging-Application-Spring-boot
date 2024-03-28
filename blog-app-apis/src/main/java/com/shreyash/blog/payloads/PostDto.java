package com.shreyash.blog.payloads;

import com.shreyash.blog.entities.Category;
import com.shreyash.blog.entities.Comment;
import com.shreyash.blog.entities.User;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class PostDto {

    private String title;
    private String content;
    private String imageName = "default.png";
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
    private Set<CommentDto> comment = new HashSet<>();
}
