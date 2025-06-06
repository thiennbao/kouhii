package com.thiennbao.kouhii.module.role;

import com.thiennbao.kouhii.common.exception.AppError;
import com.thiennbao.kouhii.common.exception.AppException;
import com.thiennbao.kouhii.module.role.data.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    @PreAuthorize("hasAuthority('READ_ROLE')")
    List<RoleResponse> getRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toResponse).toList();
    }

    @PreAuthorize("hasAuthority('READ_ROLE')")
    RoleResponse getRole(String id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new AppException(AppError.ROLE_NOT_FOUND));
        return roleMapper.toResponse(role);
    }

    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    RoleResponse createRole(RoleCreateRequest request) {
        Role role = roleMapper.toEntity(request);
        try {
            return roleMapper.toResponse(roleRepository.save(role));
        } catch (DataIntegrityViolationException exception) {
            if (exception.getCause() instanceof ConstraintViolationException) {
                throw new AppException(AppError.ROLE_NAME_CONFLICT);
            }
            throw exception;
        }
    }

    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    RoleResponse updateRole(String id, RoleUpdateRequest request) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new AppException(AppError.ROLE_NOT_FOUND));
        roleMapper.update(role, request);
        try {
            return roleMapper.toResponse(roleRepository.save(role));
        } catch (DataIntegrityViolationException exception) {
            if (exception.getCause() instanceof ConstraintViolationException) {
                throw new AppException(AppError.ROLE_NAME_CONFLICT);
            }
            throw exception;
        }
    }

    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    RoleResponse deleteRole(String id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new AppException(AppError.ROLE_NOT_FOUND));
        roleRepository.delete(role);
        return roleMapper.toResponse(role);
    }

    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    RoleResponse addPermission(String id, String permission) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new AppException(AppError.ROLE_NOT_FOUND));
        role.getPermissions().add(Permission.valueOf(permission));
        return roleMapper.toResponse(roleRepository.save(role));
    }

    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    RoleResponse removePermission(String id, String permission) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new AppException(AppError.ROLE_NOT_FOUND));
        role.getPermissions().remove(Permission.valueOf(permission));
        return roleMapper.toResponse(roleRepository.save(role));
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new AppException(AppError.ROLE_NOT_FOUND));
    }
}
