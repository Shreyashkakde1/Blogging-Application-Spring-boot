package com.shreyash.blog.payloads;

import com.shreyash.blog.entities.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String comment;
}
