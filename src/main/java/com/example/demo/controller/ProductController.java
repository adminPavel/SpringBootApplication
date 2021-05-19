package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.exeption.ValidationException;
import com.example.demo.repository.MapRepositoryProduct;
import com.example.demo.repository.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@Log
@AllArgsConstructor
@Data
public class ProductController {

    private final ProductService productService;
    private final MapRepositoryProduct mapRepositoryProduct;

    @PostMapping("/addproduct")
    public ProductDto addProduct(@RequestBody ProductDto productDto) throws ValidationException{
        log.info("Handling add products request");
        return productService.addProduct(productDto);
    }

    @GetMapping("/findAll")
    public List<ProductDto> findAllUsers() {
        log.info("Handling find all users request");
        return productService.findAll();
    }

    @GetMapping("/findProductByName")
    public ProductDto findProductOnName(@RequestParam String product_name) {
        log.info("Handling find by login request: " + product_name);
        return productService.findProductOnName(product_name);
    }

    @GetMapping("/findProductById")
    public ProductDto findById(@RequestParam Long id) throws ValidationException {
        log.info("Handling find by login request: " + id);

        return productService.findProductById(id);
    }

    @PutMapping("/update/{id}")
    public ProductDto update(@RequestBody ProductDto productDto, @PathVariable Long id) throws ValidationException {
        log.info("Handling find by login request: " + id);
        return productService.update(productDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) throws ValidationException {
        log.info("Handling delete user request: " + id);
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
