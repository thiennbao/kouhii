package com.thiennbao.kouhii.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AppError {
    // 1xxx: Authentication errors
    FORBIDDEN(HttpStatus.FORBIDDEN, 1000, "Do not have permission"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 1001, "Invalid token"),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, 1002, "Incorrect username or password"),
    // 2xxx: Validation errors
    // 3xxx: Resource errors
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, 3001, "Account not found"),
    // 4xxx: Business logic errors
    USERNAME_CONFLICT(HttpStatus.CONFLICT, 4001, "Username already exists"),
    // 5xxx: System errors
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "Internal server error");

    HttpStatus status;
    int code;
    String message;
}
