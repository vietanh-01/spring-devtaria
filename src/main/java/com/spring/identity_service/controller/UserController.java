package com.spring.identity_service.controller;

import com.spring.identity_service.dto.request.ApiResponse;
import com.spring.identity_service.dto.request.UserCreationRequest;
import com.spring.identity_service.dto.request.UserUpdateRequest;
import com.spring.identity_service.dto.response.UserResponse;
import com.spring.identity_service.entity.User;
import com.spring.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired private UserService service;

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        User user = service.createUser(request);
        apiResponse.setResult(user);
        return apiResponse;
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable Long userId) {
        return service.getUser(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@RequestBody UserUpdateRequest request, @PathVariable Long userId) {
         return service.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {

        service.delete(userId);

        return "Delete user with ID: " + userId;
    }
}
