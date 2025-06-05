package com.thiennbao.kouhii.module.role.data;

import com.thiennbao.kouhii.common.validator.EnumNameValid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionRequest {
    @NotNull(message = "ROLE_PERMISSION_NULL")
    @EnumNameValid(enumClass = Permission.class, message = "ROLE_PERMISSION_INVALID")
    String permission;
}
