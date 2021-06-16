package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MapRepositoryProduct extends JpaRepository<Product, Long> {
    Product findProductByProductname (String productname);
    @Query("SELECT u FROM Product u WHERE CONCAT(u.id,'',u.color,'',u.price,'',u.productname)LIKE %:key%")
    List<Product> searchByValue(String key);
}
