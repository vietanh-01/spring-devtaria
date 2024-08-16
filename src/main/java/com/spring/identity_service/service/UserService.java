package com.spring.identity_service.service;

import com.spring.identity_service.dto.request.UserCreationRequest;
import com.spring.identity_service.dto.request.UserUpdateRequest;
import com.spring.identity_service.dto.response.UserResponse;
import com.spring.identity_service.entity.User;
import com.spring.identity_service.exception.AppException;
import com.spring.identity_service.exception.ErrorCode;
import com.spring.identity_service.mapper.UserMapper;
import com.spring.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

     UserRepository userRepo;
     UserMapper userMapper;

    public User createUser(UserCreationRequest request) {

        if(userRepo.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);

        return userRepo.save(user);
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public UserResponse getUser(Long id) {
        return userMapper.toUserResponse(userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Can not found User with ID: " + id)));
    }

    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepo.findById(userId).get();

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepo.save(user));
    }

    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }
}
