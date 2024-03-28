package com.shreyash.blog.services.impl;

import com.shreyash.blog.entities.Category;
import com.shreyash.blog.exceptions.ResourceNotFoundException;
import com.shreyash.blog.payloads.CategoryDto;
import com.shreyash.blog.repositories.CategoryRepository;
import com.shreyash.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category map = this.modelMapper.map(categoryDto, Category.class);
        Category save = categoryRepository.save(map);
        return this.modelMapper.map(save,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));

        BeanUtils.copyProperties(categoryDto,category);
        Category updatedCategory = this.categoryRepository.save(category);
        return modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> dtoList = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());

        return dtoList;
    }

}
