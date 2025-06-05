package com.thiennbao.kouhii.module.role;

import com.thiennbao.kouhii.module.role.data.Role;
import com.thiennbao.kouhii.module.role.data.RoleRequest;
import com.thiennbao.kouhii.module.role.data.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "id", ignore = true)
    Role toEntity(RoleRequest roleRequest);

    RoleResponse toResponse(Role role);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Role role, RoleRequest roleRequest);
}
