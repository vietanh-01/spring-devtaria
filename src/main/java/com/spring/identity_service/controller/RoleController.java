package com.spring.identity_service.controller;

import com.spring.identity_service.dto.request.ApiResponse;
import com.spring.identity_service.dto.request.PermissionRequest;
import com.spring.identity_service.dto.request.RoleRequest;
import com.spring.identity_service.dto.response.PermissionResponse;
import com.spring.identity_service.dto.response.RoleResponse;
import com.spring.identity_service.service.PermissionService;
import com.spring.identity_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {

    RoleService service;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {

        return ApiResponse.<RoleResponse>builder()
                .result(service.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {

        return ApiResponse.<List<RoleResponse>>builder()
                .result(service.getAll())
                .build();
    }


    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role) {
        service.delete(role);

        return ApiResponse.<Void>builder().build();
    }
}
