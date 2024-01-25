package com.shreyash.blog.services;

import com.shreyash.blog.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    // Create
    public CategoryDto createCategory(CategoryDto categoryDto);

    // Update
    public CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);

    // Delete
    public void deleteCategory(Long categoryId);

    // Get by id
    public CategoryDto getCategoryById(Long categoryId);

    // Get All Category
    public List<CategoryDto> getAllCategory();

}
