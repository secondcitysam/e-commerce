package com.samyak.ecommerce.controller;

import com.samyak.ecommerce.dto.AuthResponseDto;
import com.samyak.ecommerce.dto.LoginRequestDto;
import com.samyak.ecommerce.dto.RegisterRequestDto;
import com.samyak.ecommerce.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponseDto register(
            @RequestBody
            RegisterRequestDto request) {

        return authService.register(
                request);
    }

    @PostMapping("/login")
    public AuthResponseDto login(
            @RequestBody
            LoginRequestDto request) {

        return authService.login(
                request);
    }
}