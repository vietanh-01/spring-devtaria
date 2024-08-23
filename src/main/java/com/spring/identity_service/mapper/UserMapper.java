package com.spring.identity_service.mapper;


import com.spring.identity_service.dto.request.UserCreationRequest;
import com.spring.identity_service.dto.request.UserUpdateRequest;
import com.spring.identity_service.dto.response.UserResponse;
import com.spring.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    //@Mapping(source = "firstName", target = "lastName") => map field firstName from User entity to field lastName in UserResponse
    //@Mapping(target = "lastName", ignore = true) => ignore field lastName in User and not map it
    UserResponse toUserResponse(User user);
}
