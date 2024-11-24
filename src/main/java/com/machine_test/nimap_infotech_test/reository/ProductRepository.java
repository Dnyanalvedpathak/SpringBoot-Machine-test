package com.machine_test.nimap_infotech_test.reository;

import com.machine_test.nimap_infotech_test.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
