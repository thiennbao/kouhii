package com.thiennbao.kouhii.common.exception;

import com.thiennbao.kouhii.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthorizationDeniedExceptionHandler {
    @ExceptionHandler(AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse<Object>> handleAuthorizationDeniedException(AuthorizationDeniedException ignore) {
        AppError appError = AppError.FORBIDDEN;
        return ResponseEntity.status(appError.getStatus()).body(ApiResponse.failure(appError));
    }
}
