package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Product;
import com.example.demo.exeption.ValidationException;
import com.example.demo.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductConverter productConverter;
    private final MapRepositoryProduct mapRepositoryProduct;

    private void validateProductDto(ProductDto productDto) throws ValidationException {
        if (isNull(productDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(productDto.getProductname()) || productDto.getProductname().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) throws ValidationException {
        validateProductDto(productDto); //валидация входящих данных о юзере
        Product product = productConverter.fromProductDtoToProduct(productDto); //конвертация из UserDto в User
        Product addedProduct = mapRepositoryProduct.save(product);// сохранение в БД
        return productConverter.fromProductToProductDto(addedProduct); //конвертация сохраненного юзера обратно в UserDTo и возврат
    }

    @Override
    public ProductDto update(ProductDto productDto, Long id) throws ValidationException {
        validateProductDto(productDto);
        Product product = productConverter.fromProductDtoToProduct(productDto);

        return mapRepositoryProduct.findById(id)
                .map(employee -> {
                    employee.setProductname(product.getProductname());
                    employee.setColor(product.getColor());
                    employee.setPrice(product.getPrice());
                    return productConverter.fromProductToProductDto(mapRepositoryProduct.save(employee));
                })
                .orElseGet(() -> {
                    product.setId(id);
                    return productConverter.fromProductToProductDto(mapRepositoryProduct.save(product));
                });
    }

    @Override
    public void deleteProduct(Long productId) {
        mapRepositoryProduct.deleteById(productId);
    }

    @Override
    public ProductDto findProductByProductname(String productname) {
        Product product = mapRepositoryProduct.findProductByProductname(productname);
//        if (product != null) {
        return productConverter.fromProductToProductDto(product);
    }
//        return null;
////    }

    @Override
    public List<ProductDto> searchByValue(String key) {
        if(key != null) {
            return productConverter.fromProductToProductDto(mapRepositoryProduct.searchByValue(key));
        }
        return mapRepositoryProduct.findAll()
                .stream()
                .map(productConverter::fromProductToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findProductById(Long id) {
        Optional<Product> product = mapRepositoryProduct.findById(id);
        return productConverter.fromProductToProductDto(product);
    }

    @Override
    public List<ProductDto> findAll() {
        return mapRepositoryProduct.findAll()
                .stream()
                .map(productConverter::fromProductToProductDto)
                .collect(Collectors.toList());
    }
}
