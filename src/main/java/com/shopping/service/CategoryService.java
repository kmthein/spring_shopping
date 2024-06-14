package com.shopping.service;

import com.shopping.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();

    public Category saveCategory(Category category);

    public Category findCategoryById(Long id);
}
