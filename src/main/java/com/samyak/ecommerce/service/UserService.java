package com.samyak.ecommerce.service;

import com.samyak.ecommerce.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User updateUser(User user);

    void deleteUser(Long id);

    User registerUser(User user);

    User loginUser(String email, String password);
}