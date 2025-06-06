package com.thiennbao.kouhii.module.account.data;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountUpdateRequest {
    @Size(min = 4, message = "ACCOUNT_USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "ACCOUNT_PASSWORD_INVALID")
    String password;

    Set<String> roles;
}
