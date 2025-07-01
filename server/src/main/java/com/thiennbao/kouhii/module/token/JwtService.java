package com.thiennbao.kouhii.module.token;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.thiennbao.kouhii.common.exception.AppError;
import com.thiennbao.kouhii.common.exception.AppException;
import com.thiennbao.kouhii.module.account.data.Account;
import com.thiennbao.kouhii.module.token.data.Token;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtService {
    @NonFinal
    @Value("${jwt.signerKey}")
    String signerKey;

    TokenRepository tokenRepository;
    TokenMapper tokenMapper;

    public String generateToken(Account account) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getId())
                .issuer("kouhii.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(30, ChronoUnit.DAYS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", account.getRoles().stream()
                        .flatMap(role -> role.getPermissions().stream())
                        .map(Enum::name)
                        .collect(Collectors.joining(" "))
                )
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return jwsObject.serialize();
    }

    public void revokeToken(String token) {
        Token revokedToken = verifyToken(token);
        tokenRepository.save(revokedToken);
    }

    public Token verifyToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            if (!signedJWT.verify(new MACVerifier(signerKey)) || signedJWT.getJWTClaimsSet().getExpirationTime().before(new Date())) {
                throw new AppException(AppError.INVALID_TOKEN);
            }
            String jit = signedJWT.getJWTClaimsSet().getJWTID();
            if (jit == null || tokenRepository.existsById(jit)) {
                throw new AppException(AppError.INVALID_TOKEN);
            }
            return tokenMapper.toToken(signedJWT);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new AppException(AppError.INVALID_TOKEN);
        }
    }
}
