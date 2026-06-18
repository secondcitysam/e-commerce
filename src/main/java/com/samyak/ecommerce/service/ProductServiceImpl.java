package com.samyak.ecommerce.service;

import com.samyak.ecommerce.dto.ProductRequestDto;
import com.samyak.ecommerce.dto.ProductResponseDto;
import com.samyak.ecommerce.entity.Product;
import com.samyak.ecommerce.exception.ProductNotFoundException;
import com.samyak.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(
            Long id) {

        Product product =
                productRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found"));

        productRepository.delete(
                product);
    }
    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository
                .findByProductNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Product> getProductsCostlierThan(Double price) {
        return productRepository
                .findProductsCostlierThan(price);
    }

    @Override
    public Page<Product> getProductsPage(
            int page,
            int size) {

        return productRepository.findAll(
                PageRequest.of(page, size));
    }

    @Override
    public List<Product> getProductsSortedByPrice() {

        return productRepository.findAll(
                Sort.by("price"));
    }

    @Override
    public List<Product> searchByName(String keyword) {

        return productRepository
                .findByProductNameContainingIgnoreCase(
                        keyword);
    }

    @Override
    public List<Product> getProductsBetweenPrice(
            Double min,
            Double max) {

        return productRepository
                .findProductsBetweenPrice(
                        min,
                        max);
    }

    private ProductResponseDto mapToDto(
            Product product) {

        return new ProductResponseDto(
                product.getProductId(),
                product.getProductName(),
                product.getPrice(),
                product.getQuantity(),
                product.getDescription());
    }

    private Product mapToEntity(
            ProductRequestDto dto) {

        Product product = new Product();

        product.setProductName(
                dto.getProductName());

        product.setPrice(
                dto.getPrice());

        product.setQuantity(
                dto.getQuantity());

        product.setDescription(
                dto.getDescription());

        return product;
    }

    @Override
    public Product updateProduct(
            Long id,
            ProductRequestDto dto) {

        Product product =
                productRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ProductNotFoundException(
                                        "Product not found"));

        product.setProductName(
                dto.getProductName());

        product.setPrice(
                dto.getPrice());

        product.setQuantity(
                dto.getQuantity());

        product.setDescription(
                dto.getDescription());

        return productRepository.save(
                product);
    }
}