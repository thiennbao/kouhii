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
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "Internal server error"),
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, 3001, "Account not found"),
    USERNAME_CONFLICT(HttpStatus.CONFLICT, 4001, "Username already exists");

    HttpStatus status;
    int code;
    String message;
}
