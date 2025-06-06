package com.thiennbao.kouhii.module.account.data;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleAddRequest {
    @NotNull(message = "ROLE_NAME_INVALID")
    String role;
}
