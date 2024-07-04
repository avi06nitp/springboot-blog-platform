package com.BlogApp.services;

import com.BlogApp.payloads.CategoryDto;
import java.util.List;

public interface CategoryService {

    // Create a new category
    CategoryDto createCategory(CategoryDto categoryDto);

    // Update an existing category
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    // Delete a category by its ID
    void deleteCategory(Integer categoryId);

    // Get a category by its ID
   CategoryDto getCategory(Integer categoryId);

    // Get all categories
    List<CategoryDto> getCategories();
}
