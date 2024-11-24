package com.machine_test.nimap_infotech_test.service;

import com.machine_test.nimap_infotech_test.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.machine_test.nimap_infotech_test.reository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAllCategories(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }

    public Category updateCategoryById(Long id, Category category){
        category.setCategoryId(id);
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
    }
}
