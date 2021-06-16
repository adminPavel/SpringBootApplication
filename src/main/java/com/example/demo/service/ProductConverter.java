package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
    public class ProductConverter {

    public ProductDto fromProductToProductDto(Optional<Product> product) {
        return ProductDto.builder()
                .id(product.orElseThrow().getId())
                .color(product.orElseThrow().getColor())
                .price(product.orElseThrow().getPrice())
                .productname(product.orElseThrow().getProductname())
                .build();
    }

    public List<ProductDto> fromProductToProductDto(List<Product> products) {
        List<ProductDto> productsDTO = new ArrayList<>();
        for (Product p: products) {
            ProductDto productDto = fromProductToProductDto(p);
            productsDTO.add(productDto);
        }
        return productsDTO;
    }

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
