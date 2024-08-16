package com.spring.identity_service.dto.request;

import com.spring.identity_service.exception.ErrorCode;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3, message = "Username must be at least 3 characters")
    String username;
    @Size(min = 8, max = 10, message = "LIMIT_VALIDATE")
    String password;
    String lastName;
    String firstName;

    LocalDate dob;
}
