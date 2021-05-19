package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface ProductIdRepository extends JpaRepository<Product, Long> {
        Product findProductById(Long id);
}
