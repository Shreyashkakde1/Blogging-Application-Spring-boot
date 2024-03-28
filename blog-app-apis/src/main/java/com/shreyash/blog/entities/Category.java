package com.shreyash.blog.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    @Column(name = "title")
    private String categoryTitle;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(name = "description")
    private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> post = new ArrayList<>();
}
