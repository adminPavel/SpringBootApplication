package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductNameRepository extends JpaRepository<Product, Long> {

    Product findProductByProductname (String productname);

}
