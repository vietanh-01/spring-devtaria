package com.spring.identity_service.service;

import com.spring.identity_service.dto.request.UserCreationRequest;
import com.spring.identity_service.dto.request.UserUpdateRequest;
import com.spring.identity_service.dto.response.UserResponse;
import com.spring.identity_service.entity.User;
import com.spring.identity_service.enums.Role;
import com.spring.identity_service.exception.AppException;
import com.spring.identity_service.exception.ErrorCode;
import com.spring.identity_service.mapper.UserMapper;
import com.spring.identity_service.repository.RoleRepository;
import com.spring.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

     UserRepository userRepo;
     UserMapper userMapper;
     RoleRepository roleRepo;
     PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {

        if(userRepo.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

       // user.setRoles(roles);
        userRepo.save(user);

        return userMapper.toUserResponse(user);
    }

   // @PreAuthorize("hasRole('ADMIN')") // only admin can access, check before the method is called
    @PreAuthorize("hasAuthority('APPROVE_POST')")
    public List<UserResponse> getUsers() {
        log.info("In method get Users");
        return userRepo.findAll().stream().map(user -> userMapper.toUserResponse(user)).toList();
    }

    @PostAuthorize("returnObject.username.equals(authentication.name)") // check after the method is called -> can not reverse the action
    public UserResponse getUser(Long id) {
        log.info("In method get User by ID");

        return userMapper.toUserResponse(userRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public UserResponse getMyInfo() {
        SecurityContext context = SecurityContextHolder.getContext();// after spring security verify user
        String name = context.getAuthentication().getName();// information about user has been saved in SecurityContextHolder

        User user = userRepo.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepo.findById(userId).get();

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepo.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepo.save(user));
    }

    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }
}
