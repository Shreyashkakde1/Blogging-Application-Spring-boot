package com.shreyash.blog.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    // Unique identifier for the post
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    // Title of the post
    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    @Column(name = "post_title")
    private String title;

    // Content of the post
    @NotBlank(message = "Content cannot be blank")
    @Column(name = "post_content")
    private String content;

    // Image name associated with the post
    @NotBlank(message = "Image name cannot be blank")
    @Column(name = "post_image_name")
    private String imageName;

    // Date when the post was added
    @NotNull(message = "Added date cannot be null")
    @Column(name = "post_added_date")
    private Date addedDate;

    // Many-to-One relationship with the Category entity
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Many-to-One relationship with the User entity
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();



}
