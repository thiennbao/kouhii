package com.thiennbao.kouhii.module.auth;

import com.thiennbao.kouhii.common.exception.AppError;
import com.thiennbao.kouhii.common.exception.AppException;
import com.thiennbao.kouhii.module.account.AccountRepository;
import com.thiennbao.kouhii.module.account.data.Account;
import com.thiennbao.kouhii.module.auth.data.AuthRequest;
import com.thiennbao.kouhii.module.auth.data.AuthResponse;
import com.thiennbao.kouhii.module.auth.data.RevokeRequest;
import com.thiennbao.kouhii.module.token.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {
    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;

    AuthResponse authenticate(AuthRequest request) {
        Account account = accountRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(AppError.LOGIN_FAILED));
        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new AppException(AppError.LOGIN_FAILED);
        }
        return new AuthResponse(jwtService.generateToken(account));
    }

    Void revoke(RevokeRequest request) {
        jwtService.revokeToken(request.getToken());
        return null;
    }
}
