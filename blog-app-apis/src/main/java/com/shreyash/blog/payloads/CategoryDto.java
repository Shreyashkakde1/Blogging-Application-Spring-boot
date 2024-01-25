package com.shreyash.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {


    private Long categoryId;
    private String categoryTitle;
    private String categoryDescription;
}
