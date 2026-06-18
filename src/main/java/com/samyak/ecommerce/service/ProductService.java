package com.samyak.ecommerce.service;

import com.samyak.ecommerce.dto.ProductRequestDto;
import com.samyak.ecommerce.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product updateProduct(Product product);

    void deleteProduct(Long id);

    List<Product> searchProducts(String keyword);

    List<Product> getProductsCostlierThan(Double price);

    Page<Product> getProductsPage(
            int page,
            int size);

    List<Product> getProductsSortedByPrice();

    List<Product> searchByName(String keyword);

    List<Product> getProductsBetweenPrice(
            Double min,
            Double max);

    Product updateProduct(
            Long id,
            ProductRequestDto dto);

}