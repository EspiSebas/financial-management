package com.example.financial.management.service;

import com.example.financial.management.dto.category.CategoryRequest;
import com.example.financial.management.dto.category.CategoryResponse;
import com.example.financial.management.entity.Category;
import com.example.financial.management.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(String name, String type){
        Category category = new Category();

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("The name is required.");
        }

        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("The type is required.");
        }

        category.setName(name);
        category.setType(type);

        categoryRepository.save(category);

    }


    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public List<CategoryResponse> getAllCategory(){
        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getName(),
                        category.getType()
                ))
                .toList();
    }


    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest){
        Category cat = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (categoryRequest.getName() == null || categoryRequest.getName().isBlank()) {
            throw new IllegalArgumentException("The name is required.");
        }

        if (categoryRequest.getType() == null || categoryRequest.getType().isBlank()) {
            throw new IllegalArgumentException("The type is required.");
        }
        cat.setName(categoryRequest.getName());
        cat.setType(categoryRequest.getType());
        categoryRepository.save(cat);

        return new CategoryResponse(
                cat.getId(),
                cat.getName(),
                cat.getType()
        );
    }
}
