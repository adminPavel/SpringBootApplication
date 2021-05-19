package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Component;

    @Component
    public class ProductConverter {

        public ProductDto fromProductToProductDto(Product product) {
            return ProductDto.builder()
                    .id(product.getId())
                    .productname(product.getProductname())
                    .color(product.getColor())
                    .price(product.getPrice())
                    .build();
        }

        public Product fromProductDtoToProduct(ProductDto productDto) {
            return Product.builder()
                    .id(productDto.getId())
                    .productname(productDto.getProductname())
                    .color(productDto.getColor())
                    .price(productDto.getPrice())
                    .build();
        }
    }
