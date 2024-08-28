package com.spring.identity_service.dto.request;

import com.spring.identity_service.exception.ErrorCode;
import com.spring.identity_service.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 4, message = "USERNAME_INVALID")
    String username;
    @Size(min = 8, max = 10, message = "INVALID_PASSWORD")
    String password;
    String lastName;
    String firstName;
    @DobConstraint(min = 16, message = "INVALID_DOB")
    LocalDate dob;
}
