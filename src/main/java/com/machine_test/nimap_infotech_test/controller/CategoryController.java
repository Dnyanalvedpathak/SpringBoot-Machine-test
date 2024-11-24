package com.machine_test.nimap_infotech_test.controller;

import com.machine_test.nimap_infotech_test.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.machine_test.nimap_infotech_test.service.CategoryService;

import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public Page<Category> getAllCategories(Pageable pageable){
        return categoryService.getAllCategories(pageable);
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable Long id, @RequestBody Category category){
        return ResponseEntity.ok(categoryService.updateCategoryById(id,category));
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }
}
