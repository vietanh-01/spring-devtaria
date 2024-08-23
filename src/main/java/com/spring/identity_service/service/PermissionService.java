package com.spring.identity_service.service;

import com.spring.identity_service.dto.request.PermissionRequest;
import com.spring.identity_service.dto.response.PermissionResponse;
import com.spring.identity_service.entity.Permission;
import com.spring.identity_service.mapper.PermissionMapper;
import com.spring.identity_service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {

    PermissionRepository repo;
    PermissionMapper mapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = mapper.toPermission(request);
        repo.save(permission);

        return mapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = repo.findAll();

        return permissions.stream().map(mapper::toPermissionResponse).collect(Collectors.toList());
    }

    public void delete(String permission) {
        repo.deleteById(permission);
    }
}
