package com.example.financial.management.controller;

import com.example.financial.management.dto.category.CategoryRequest;
import com.example.financial.management.dto.category.CategoryResponse;
import com.example.financial.management.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<Void> createCategory(@RequestBody CategoryRequest categoryRequest){
        categoryService.createCategory(categoryRequest.getName(),categoryRequest.getType());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);

        return ResponseEntity.ok("Deleted correctly");
    }

    @GetMapping("/categories")
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategory();
    }


    @PatchMapping("/category/{id}")
    public ResponseEntity<CategoryResponse> updateLog(
            @PathVariable Long id,
            @RequestBody CategoryRequest request
    ) {
        CategoryResponse updated = categoryService.updateCategory(id, request);

        return ResponseEntity.ok(updated);
    }


}
