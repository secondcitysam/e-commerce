package com.samyak.ecommerce.repository;

import com.samyak.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    List<Product> findByProductNameContainingIgnoreCase(String keyword);

    @Query("""
       SELECT p
       FROM Product p
       WHERE p.price > ?1
       """)
    List<Product> findProductsCostlierThan(Double price);

    @Query("""
       SELECT p
       FROM Product p
       WHERE p.price BETWEEN ?1 AND ?2
       """)
    List<Product> findProductsBetweenPrice(
            Double min,
            Double max);
}