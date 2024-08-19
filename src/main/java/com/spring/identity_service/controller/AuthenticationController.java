package com.spring.identity_service.controller;

import com.nimbusds.jose.JOSEException;
import com.spring.identity_service.dto.request.ApiResponse;
import com.spring.identity_service.dto.request.AuthenticationRequest;
import com.spring.identity_service.dto.request.IntrospectRequest;
import com.spring.identity_service.dto.response.AuthenticationResponse;
import com.spring.identity_service.dto.response.IntrospectResponse;
import com.spring.identity_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService service;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = service.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> verifyToken(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
            var result = service.verify(request);

            return ApiResponse.<IntrospectResponse>builder()
                    .result(result)
                    .build();
    }

}
