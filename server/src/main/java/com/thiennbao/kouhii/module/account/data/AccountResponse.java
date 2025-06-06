package com.thiennbao.kouhii.module.account.data;

import com.thiennbao.kouhii.module.role.data.RoleResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    String id;
    String username;
    Set<RoleResponse> roles = new HashSet<>();
}
