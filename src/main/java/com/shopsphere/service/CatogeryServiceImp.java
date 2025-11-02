package com.shopsphere.service;

import com.shopsphere.entity.Category;

import com.shopsphere.entity.Product;

import com.shopsphere.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatogeryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

//    @Autowired
//    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
//
    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
//        Category category = categoryMapper.getCategoryById(id);
        Category category = categoryRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Category not found"));

        // Force initialize products (to load before session closes)
        category.getProducts().size();
//        List<ProductDTO> products = category.getProducts().stream()
//                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getDescription(),p.getPrice(), p.getStock()))
//                .toList();
//
//        return new CategoryResponse(category.getId(), category.getName(), products);
      return  category;
    }

    @Override
    public Category createCategory(Category category) {
        if (category.getProducts() != null) {
            for (Product product : category.getProducts()) {
                product.setCategory(category); // 👈 link back to the parent

            }
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    return categoryRepository.save(category);
                })
                .orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    @Override
    public String deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found with id " + id);
        }
        String name = categoryRepository.findById(id).get().getName();
        categoryRepository.deleteById(id);

        return "Category with id " + id + " "+ name + " has been deleted";
    }
}
