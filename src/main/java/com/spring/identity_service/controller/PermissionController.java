package com.spring.identity_service.controller;

import com.spring.identity_service.dto.request.ApiResponse;
import com.spring.identity_service.dto.request.PermissionRequest;
import com.spring.identity_service.dto.response.PermissionResponse;
import com.spring.identity_service.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {

    PermissionService service;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {

        return ApiResponse.<PermissionResponse>builder()
                .result(service.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {

        return ApiResponse.<List<PermissionResponse>>builder()
                .result(service.getAll())
                .build();
    }


    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {
        service.delete(permission);

        return ApiResponse.<Void>builder().build();
    }
}
