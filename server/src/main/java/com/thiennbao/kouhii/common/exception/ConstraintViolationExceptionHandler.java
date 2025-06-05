package com.thiennbao.kouhii.common.exception;

import com.thiennbao.kouhii.common.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Order(0)
@ControllerAdvice
public class ConstraintViolationExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ApiResponse<Object>> handleConstraintViolationException(ConstraintViolationException exception) {
        String message = exception.getConstraintViolations().iterator().next().getMessage();
        AppError appError;
        try {
            appError = AppError.valueOf(message);
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error("{}: {}", illegalArgumentException.getClass(), illegalArgumentException.getMessage());
            appError = AppError.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(appError.getStatus()).body(ApiResponse.failure(appError));
    }
}
