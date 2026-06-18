package com.samyak.ecommerce.controller.view;

import com.samyak.ecommerce.dto.RegisterRequestDto;
import com.samyak.ecommerce.entity.Product;
import com.samyak.ecommerce.service.AuthService;
import com.samyak.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ViewController {

    private final AuthService authService;
    private final ProductService productService;
    public ViewController(AuthService authService, ProductService productService) {
        this.authService = authService;
        this.productService = productService;
    }
    @GetMapping("/dashboard")
    public String dashboard(
            Model model) {

        List<Product> products =
                productService.getAllProducts();

        long totalProducts =
                products.size();

        int totalInventory =
                products.stream()
                        .mapToInt(
                                Product::getQuantity)
                        .sum();

        double averagePrice =
                products.stream()
                        .mapToDouble(
                                Product::getPrice)
                        .average()
                        .orElse(0);

        model.addAttribute(
                "totalProducts",
                totalProducts);

        model.addAttribute(
                "totalInventory",
                totalInventory);

        model.addAttribute(
                "averagePrice",
                averagePrice);

        return "dashboard";
    }
    @GetMapping("/login")
    public String login() {

        return "auth/login";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute RegisterRequestDto request) {

        authService.register(request);

        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute(
                "user",
                new RegisterRequestDto());

        return "auth/register";
    }




}