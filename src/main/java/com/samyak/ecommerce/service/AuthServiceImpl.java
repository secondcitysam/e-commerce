package com.samyak.ecommerce.service;

import com.samyak.ecommerce.dto.AuthResponseDto;
import com.samyak.ecommerce.dto.LoginRequestDto;
import com.samyak.ecommerce.dto.RegisterRequestDto;
import com.samyak.ecommerce.entity.User;
import com.samyak.ecommerce.exception.UserAlreadyExistsException;
import com.samyak.ecommerce.repository.UserRepository;
import com.samyak.ecommerce.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl
        implements AuthService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager
            authenticationManager;

    private final BCryptPasswordEncoder
            passwordEncoder;
    public AuthServiceImpl(
            UserRepository userRepository,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager =
                authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDto register(
            RegisterRequestDto request) {

        if (userRepository
                .existsByEmail(request.getEmail())) {

            throw new UserAlreadyExistsException(
                    "Email already registered");
        }

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        userRepository.save(user);

        String token =
                jwtUtil.generateToken(
                        user.getEmail());

        return new AuthResponseDto(token);
    }
    @Override
    public AuthResponseDto login(
            LoginRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        String token =
                jwtUtil.generateToken(
                        request.getEmail());

        return new AuthResponseDto(token);
    }
}