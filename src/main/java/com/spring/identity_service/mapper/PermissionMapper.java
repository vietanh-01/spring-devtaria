package com.spring.identity_service.mapper;


import com.spring.identity_service.dto.request.PermissionRequest;
import com.spring.identity_service.dto.request.UserCreationRequest;
import com.spring.identity_service.dto.request.UserUpdateRequest;
import com.spring.identity_service.dto.response.PermissionResponse;
import com.spring.identity_service.dto.response.UserResponse;
import com.spring.identity_service.entity.Permission;
import com.spring.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
