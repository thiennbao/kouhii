package com.thiennbao.kouhii.module.account.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreateRequest {
    @NotNull(message = "ACCOUNT_USERNAME_INVALID")
    @Size(min = 4, message = "ACCOUNT_USERNAME_INVALID")
    String username;

    @NotNull(message = "ACCOUNT_PASSWORD_INVALID")
    @Size(min = 8, message = "ACCOUNT_PASSWORD_INVALID")
    String password;

    Set<String> roles = new HashSet<>();
}
