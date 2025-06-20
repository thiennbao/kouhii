package com.thiennbao.kouhii.common.exception;

import com.thiennbao.kouhii.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Order
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse<Object>> handleAllException(Exception exception) {
        log.error("{}: {}", exception.getClass(), exception.getMessage());
        AppError appError = AppError.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(appError.getStatus()).body(ApiResponse.failure(appError));
    }
}
