package com.spring.identity_service.service;


import com.spring.identity_service.dto.request.RoleRequest;
import com.spring.identity_service.dto.response.RoleResponse;
import com.spring.identity_service.entity.Permission;
import com.spring.identity_service.mapper.RoleMapper;
import com.spring.identity_service.repository.PermissionRepository;
import com.spring.identity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper mapper;
    public RoleResponse create(RoleRequest request) {
        var role = mapper.toRole(request);

        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        roleRepository.save(role);

        return mapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        return  roleRepository.findAll()
                .stream()
                .map(mapper::toRoleResponse)
                .toList();
    }

    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
