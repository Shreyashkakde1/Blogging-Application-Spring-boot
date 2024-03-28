package com.shreyash.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @NotBlank(message = "Category title cannot be blank")
    @Size(max = 255,min = 4, message = "Category title must be at most 255 characters and more then 4 character" )
    private String categoryTitle;

    @Size(max = 1000, message = "Category description must be at most 1000 characters")
    private String categoryDescription;
}
