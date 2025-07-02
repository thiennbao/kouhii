package com.thiennbao.kouhii.module.menu;

import com.thiennbao.kouhii.common.response.ApiResponse;
import com.thiennbao.kouhii.module.menu.data.MenuItemCreateRequest;
import com.thiennbao.kouhii.module.menu.data.MenuItemResponse;
import com.thiennbao.kouhii.module.menu.data.MenuItemUpdateRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuController {
    MenuService menuService;

    @GetMapping
    ApiResponse<List<MenuItemResponse>> getMenuItems() {
        return ApiResponse.success(menuService.getMenuItems());
    }

    @GetMapping("/{id}")
    ApiResponse<MenuItemResponse> getMenuItem(@PathVariable("id") String id) {
        return ApiResponse.success(menuService.getMenuItem(id));
    }

    @PostMapping
    ApiResponse<MenuItemResponse> createMenuItem(@Valid @RequestBody MenuItemCreateRequest request) {
        return ApiResponse.success(menuService.createMenuItem(request));
    }

    @PutMapping("/{id}")
    ApiResponse<MenuItemResponse> updateMenuItem(@PathVariable("id") String id, @Valid @RequestBody MenuItemUpdateRequest request) {
        return ApiResponse.success(menuService.updateMenuItem(id, request));
    }

    @DeleteMapping("/{id}")
    ApiResponse<MenuItemResponse> deleteMenuItem(@PathVariable("id") String id) {
        return ApiResponse.success(menuService.deleteMenuItem(id));
    }
}
