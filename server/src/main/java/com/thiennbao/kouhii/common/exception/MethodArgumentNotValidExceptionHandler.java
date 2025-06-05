package com.thiennbao.kouhii.common.exception;

import com.thiennbao.kouhii.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Order(0)
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getAllErrors().getLast().getDefaultMessage();
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
