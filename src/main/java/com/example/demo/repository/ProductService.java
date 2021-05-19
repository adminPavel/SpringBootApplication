package com.example.demo.repository;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exeption.ValidationException;

import java.util.List;

public interface ProductService {

    ProductDto addProduct (ProductDto productDto) throws ValidationException;
    void deleteProduct (Long userId) throws ValidationException;
    ProductDto findProductOnName (String productname);
    ProductDto findProductById(Long id) throws ValidationException;
    List<ProductDto> findAll();
    ProductDto update(ProductDto productDto, Long id) throws ValidationException;

}
