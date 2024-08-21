package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAllCategories();
    void createCategory(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Long categoryID, Category category);
}
