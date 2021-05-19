package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepositoryProduct extends JpaRepository<Product, Long> {
}
