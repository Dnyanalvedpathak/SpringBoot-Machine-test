package com.machine_test.nimap_infotech_test.service;

import com.machine_test.nimap_infotech_test.model.Category;
import com.machine_test.nimap_infotech_test.model.Product;
import com.machine_test.nimap_infotech_test.reository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.machine_test.nimap_infotech_test.reository.ProductRepository;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Product> getAllProducts(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    public Product addProduct(Product product){

        Long categoryId = product.getCategory().getCategoryId();
        if(product.getCategory() == null || categoryId == null){
            throw new IllegalArgumentException("Category id must not be null");
        }
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException("Category with id" + categoryId+ "is not found"));
        //set fetched category to product
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Product updateProductById(Long id, Product product){
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product with id " +id+ " not found"));

        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductPrice(product.getProductPrice());

        if(product.getCategory() != null && product.getCategory().getCategoryId() != null){
            Category category = categoryRepository.findById(product.getCategory().getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Catgeory not found"));
            existingProduct.setCategory(category);
        }
        return productRepository.save(existingProduct);
    }

    public void deleteProductById(Long id){
         productRepository.deleteById(id);
    }
}
