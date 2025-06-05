package com.thiennbao.kouhii.module.role;

import com.thiennbao.kouhii.module.role.data.Role;
import com.thiennbao.kouhii.module.role.data.RoleCreateRequest;
import com.thiennbao.kouhii.module.role.data.RoleResponse;
import com.thiennbao.kouhii.module.role.data.RoleUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {
    @Mapping(target = "id", ignore = true)
    Role toEntity(RoleCreateRequest request);

    RoleResponse toResponse(Role role);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Role role, RoleUpdateRequest request);
}
