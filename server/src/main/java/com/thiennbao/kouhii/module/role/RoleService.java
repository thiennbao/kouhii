package com.thiennbao.kouhii.module.role;

import com.thiennbao.kouhii.common.exception.AppError;
import com.thiennbao.kouhii.common.exception.AppException;
import com.thiennbao.kouhii.module.role.data.Role;
import com.thiennbao.kouhii.module.role.data.RoleRequest;
import com.thiennbao.kouhii.module.role.data.RoleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    List<RoleResponse> getRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toResponse).toList();
    }

    RoleResponse getRole(String id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new AppException(AppError.ROLE_NOT_FOUND));
        return roleMapper.toResponse(role);
    }

    RoleResponse createRole(RoleRequest request) {
        Role role = roleMapper.toEntity(request);
        try {
            return roleMapper.toResponse(roleRepository.save(role));
        } catch (DataIntegrityViolationException _) {
            throw new AppException(AppError.ROLE_NAME_CONFLICT);
        }
    }

    RoleResponse updateRole(String id, RoleRequest request) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new AppException(AppError.ROLE_NOT_FOUND));
        roleMapper.update(role, request);
        try {
            return roleMapper.toResponse(roleRepository.save(role));
        } catch (DataIntegrityViolationException _) {
            throw new AppException(AppError.ROLE_NAME_CONFLICT);
        }
    }

    RoleResponse deleteRole(String id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new AppException(AppError.ROLE_NOT_FOUND));
        roleRepository.delete(role);
        return roleMapper.toResponse(role);
    }
}
