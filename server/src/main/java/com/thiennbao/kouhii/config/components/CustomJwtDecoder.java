package com.thiennbao.kouhii.config.components;

import com.thiennbao.kouhii.common.exception.AppException;
import com.thiennbao.kouhii.module.token.JwtService;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.signerKey}")
    String signerKey;

    final JwtService jwtService;

    NimbusJwtDecoder nimbusJwtDecoder;

    @PostConstruct
    void init() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), MacAlgorithm.HS512.name());
        nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
    }

    @Override
    public Jwt decode(String token) {
        try {
            jwtService.verifyToken(token);
        } catch (AppException e) {
            throw new BadJwtException(e.getMessage());
        }
        return nimbusJwtDecoder.decode(token);
    }
}
