package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

    @Component
    public class ProductConverter {

        public ProductDto fromProductToProductDto(ProductDto productDto) {
            return ProductDto.builder()
                    .id(productDto.getId())
                    .product_name(productDto.getProduct_name())
                    .color(productDto.getColor())
                    .price(productDto.getPrice())
                    .build();
        }

        public Product fromProductDtoToProduct(Product product) {
            return Product.builder()
                    .id(product.getId())
                    .product_name(product.getProduct_name())
                    .color(product.getColor())
                    .price(product.getPrice())
                    .build();
        }
    }
