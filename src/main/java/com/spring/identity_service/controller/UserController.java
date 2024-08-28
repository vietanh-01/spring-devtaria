package com.spring.identity_service.controller;

import com.spring.identity_service.dto.request.ApiResponse;
import com.spring.identity_service.dto.request.UserCreationRequest;
import com.spring.identity_service.dto.request.UserUpdateRequest;
import com.spring.identity_service.dto.response.UserResponse;
import com.spring.identity_service.mapper.UserMapper;
import com.spring.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired private UserService service;
    @Autowired private UserMapper userMapper;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        log.info("Controller: create user");
        return ApiResponse.<UserResponse>builder()
                .result(service.createUser(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(
                authority -> log.info(authority.getAuthority())
        );

        return ApiResponse.<List<UserResponse>>builder()
                .result(service.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable Long userId) {
        return service.getUser(userId);
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@RequestBody UserUpdateRequest request, @PathVariable Long userId) {
         return ApiResponse.<UserResponse>builder()
                 .result(service.updateUser(userId, request))
                 .build();
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Long userId) {

        service.delete(userId);

        return "Delete user with ID: " + userId;
    }

    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(service.getMyInfo())
                .build();
    }
}
