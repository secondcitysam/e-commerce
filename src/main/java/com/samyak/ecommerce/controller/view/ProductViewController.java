package com.samyak.ecommerce.controller.view;

import com.samyak.ecommerce.entity.Product;
import com.samyak.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(
            ProductService productService) {

        this.productService = productService;
    }

    @GetMapping("/products-ui")
    public String products(
            Model model) {

        List<Product> products =
                productService.getAllProducts();

        model.addAttribute(
                "products",
                products);

        return "products/list";
    }

    @GetMapping("/products/create")
    public String createProduct(
            Model model) {

        model.addAttribute(
                "product",
                new Product());

        return "products/form";
    }

    @PostMapping("/products/save")
    public String saveProduct(
            @ModelAttribute Product product) {

        productService.saveProduct(product);

        return "redirect:/products-ui";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(
            @PathVariable Long id,
            Model model) {

        Product product =
                productService
                        .getProductById(id)
                        .orElseThrow();

        model.addAttribute(
                "product",
                product);

        return "products/form";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return "redirect:/products-ui";
    }

    @GetMapping("/products-ui/search")
    public String searchProducts(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "products",
                productService
                        .searchProducts(keyword));

        model.addAttribute(
                "keyword",
                keyword);

        return "products/list";
    }

    @GetMapping("/products-ui/sort")
    public String sortProducts(
            Model model) {

        model.addAttribute(
                "products",
                productService
                        .getProductsSortedByPrice());

        return "products/list";
    }
}