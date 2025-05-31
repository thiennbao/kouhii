package com.thiennbao.kouhii.module.auth.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyRequest {
    String token;
}
