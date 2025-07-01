package com.thiennbao.kouhii.module.auth.data;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RevokeRequest {
    @NotNull(message = "AUTH_REVOKE_INVALID")
    String token;
}
