package com.thiennbao.kouhii.module.auth;

import com.thiennbao.kouhii.common.response.ApiResponse;
import com.thiennbao.kouhii.module.auth.data.AuthRequest;
import com.thiennbao.kouhii.module.auth.data.AuthResponse;
import com.thiennbao.kouhii.module.auth.data.RevokeRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("/login")
    ApiResponse<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ApiResponse.success(authService.authenticate(request));
    }

    @PostMapping("/logout")
    ApiResponse<Void> revoke(@Valid @RequestBody RevokeRequest request) {
        return ApiResponse.success(authService.revoke(request));
    }
}
