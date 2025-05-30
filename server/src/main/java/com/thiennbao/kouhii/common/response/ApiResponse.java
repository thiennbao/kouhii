package com.thiennbao.kouhii.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.thiennbao.kouhii.common.exception.AppError;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    boolean success;
    T data;
    AppError error;
    @Builder.Default
    String timestamp = Instant.now().toString();

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder().success(true).data(data).build();
    }

    public static <T> ApiResponse<T> failure(AppError appError) {
        return ApiResponse.<T>builder().success(false).error(appError).build();
    }
}
