package com.spring.identity_service.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_EXISTED(1001, "User existed"),
    LIMIT_VALIDATE(5000, "Password must be at least 8 characters and maximum is 10 chacracters"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception"),
    USER_NOT_EXISTED(1005, "User not exsited"),
    UNAUTHENTICATED(1006, "Unauthenticated")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;
}
