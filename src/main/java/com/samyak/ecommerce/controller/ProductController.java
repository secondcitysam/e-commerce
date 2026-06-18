package com.samyak.ecommerce.controller;

import com.samyak.ecommerce.dto.ProductRequestDto;
import com.samyak.ecommerce.entity.Product;
import com.samyak.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseBody
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @ResponseBody
    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }


    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "Product Controller Working";
    }

    @ResponseBody
    @GetMapping("/page")
    public Page<Product> getProductsPage(
            @RequestParam int page,
            @RequestParam int size) {

        return productService
                .getProductsPage(page, size);
    }
    @ResponseBody
    @GetMapping("/sorted")
    public List<Product> getSortedProducts() {

        return productService
                .getProductsSortedByPrice();
    }

    @ResponseBody
    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam String keyword) {

        return productService.searchByName(keyword);
    }

    @ResponseBody
    @GetMapping("/price-range")
    public List<Product> getProductsByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max) {

        return productService
                .getProductsBetweenPrice(min, max);
    }

    @ResponseBody
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody
            ProductRequestDto dto) {

        return productService
                .updateProduct(id, dto);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return "Product Deleted Successfully";
    }
}