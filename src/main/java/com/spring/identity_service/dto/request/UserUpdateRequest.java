package com.spring.identity_service.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserUpdateRequest {
    private String password;
    private String lastName;
    private String firstName;
    private LocalDate dob;
    List<String> roles;
}
