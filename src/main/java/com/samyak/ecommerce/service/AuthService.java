package com.samyak.ecommerce.service;

import com.samyak.ecommerce.dto.AuthResponseDto;
import com.samyak.ecommerce.dto.LoginRequestDto;
import com.samyak.ecommerce.dto.RegisterRequestDto;

public interface AuthService {

    AuthResponseDto register(
            RegisterRequestDto request);

    AuthResponseDto login(
            LoginRequestDto request);
}