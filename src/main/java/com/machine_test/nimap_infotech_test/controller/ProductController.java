package com.machine_test.nimap_infotech_test.controller;

import com.machine_test.nimap_infotech_test.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.machine_test.nimap_infotech_test.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public Page<Product> getAllProducts(Pageable pageable){
        return productService.getAllProducts(pageable);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        try {
            Product savedProduct = productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id)
                .map(product -> {
                    product.getCategory();
                    return ResponseEntity.ok(product);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long id, @RequestBody Product product){
        return ResponseEntity.ok(productService.updateProductById(id, product));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }

}
