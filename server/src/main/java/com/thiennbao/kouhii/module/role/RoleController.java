package com.thiennbao.kouhii.module.role;

import com.thiennbao.kouhii.common.response.ApiResponse;
import com.thiennbao.kouhii.module.role.data.Permission;
import com.thiennbao.kouhii.module.role.data.PermissionRequest;
import com.thiennbao.kouhii.module.role.data.RoleRequest;
import com.thiennbao.kouhii.module.role.data.RoleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @GetMapping
    ApiResponse<List<RoleResponse>> getRoles() {
        return ApiResponse.success(roleService.getRoles());
    }

    @GetMapping("/{id}")
    ApiResponse<RoleResponse> getRole(@PathVariable String id) {
        return ApiResponse.success(roleService.getRole(id));
    }

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
        return ApiResponse.success(roleService.createRole(request));
    }

    @PutMapping("/{id}")
    ApiResponse<RoleResponse> updateRole(@PathVariable String id, @RequestBody RoleRequest request) {
        return ApiResponse.success(roleService.updateRole(id, request));
    }

    @DeleteMapping("/{id}")
    ApiResponse<RoleResponse> deleteRole(@PathVariable String id) {
        return ApiResponse.success(roleService.deleteRole(id));
    }

    @PostMapping("/{id}/permissions")
    ApiResponse<RoleResponse> addPermission(@PathVariable String id, @RequestBody PermissionRequest request) {
        return ApiResponse.success(roleService.addPermission(id, request.getPermission()));
    }

    @DeleteMapping("/{id}/permissions/{permission}")
    ApiResponse<RoleResponse> removePermission(@PathVariable String id, @PathVariable Permission permission) {
        return ApiResponse.success(roleService.removePermission(id, permission));
    }
}
