package com.thiennbao.kouhii.module.role.data;

import com.thiennbao.kouhii.common.validator.EnumNameValid;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleUpdateRequest {
    @Size(min = 1, message = "ROLE_NAME_INVALID")
    String name;

    String description;

    Set<@EnumNameValid(enumClass = Permission.class, message = "ROLE_PERMISSION_INVALID") String> permissions;
}
