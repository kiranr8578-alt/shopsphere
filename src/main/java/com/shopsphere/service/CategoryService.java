package com.shopsphere.service;

import com.shopsphere.entity.Category;
//import com.shopsphere.entity.CategoryResponse
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();

    @EntityGraph(attributePaths = "products")
    Category getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    String deleteCategory(Long id);
}
