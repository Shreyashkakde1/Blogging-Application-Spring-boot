package com.shreyash.blog.controllers;

import com.shreyash.blog.payloads.ApiResponse;
import com.shreyash.blog.payloads.CategoryDto;
import com.shreyash.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class for managing operations related to categories.
 * Handles HTTP requests at the endpoint "/api/categories".
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    /**
     * Autowired instance of CategoryService for handling category-related business logic.
     */
    @Autowired
    CategoryService categoryService;

    /**
     * Create a new category.
     *
     * @param categoryDto The category data to be created.
     * @return ResponseEntity with the created category data and HTTP status 201 (Created).
     */
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    /**
     * Update an existing category.
     *
     * @param categoryDto  The updated category data.
     * @param categoryId   The ID of the category to be updated.
     * @return ResponseEntity with the updated category data and HTTP status 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable("id") Long categoryId) {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    /**
     * Delete a category by its ID.
     *
     * @param categoryId The ID of the category to be deleted.
     * @return ResponseEntity with a success message and HTTP status 200 (OK).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Delete Successfully", true), HttpStatus.OK);
    }

    /**
     * Get a category by its ID.
     *
     * @param categoryId The ID of the category to be retrieved.
     * @return ResponseEntity with the category data and HTTP status 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryId) {
        CategoryDto categoryById = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryById, HttpStatus.OK);
    }

    /**
     * Get all categories.
     *
     * @return ResponseEntity with a list of all categories and HTTP status 200 (OK).
     */
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> allCategory = categoryService.getAllCategory();
        return new ResponseEntity<>(allCategory, HttpStatus.OK);
    }
}
