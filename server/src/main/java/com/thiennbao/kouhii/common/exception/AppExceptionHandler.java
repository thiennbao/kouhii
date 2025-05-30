package com.thiennbao.kouhii.common.exception;

import com.thiennbao.kouhii.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse<Object>> handleAppException(AppException appException) {
        return ResponseEntity.status(appException.getAppError().getStatus()).body(ApiResponse.failure(appException.getAppError()));
    }
}
