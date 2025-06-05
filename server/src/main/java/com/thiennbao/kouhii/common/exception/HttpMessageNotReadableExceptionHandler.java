package com.thiennbao.kouhii.common.exception;

import com.thiennbao.kouhii.common.response.ApiResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(0)
@ControllerAdvice
public class HttpMessageNotReadableExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ApiResponse<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ignore) {
        AppError appError = AppError.BAD_REQUEST;
        return ResponseEntity.status(appError.getStatus()).body(ApiResponse.failure(appError));
    }

}
