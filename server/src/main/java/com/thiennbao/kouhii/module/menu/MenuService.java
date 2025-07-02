package com.thiennbao.kouhii.module.menu;

import com.thiennbao.kouhii.common.exception.AppError;
import com.thiennbao.kouhii.common.exception.AppException;
import com.thiennbao.kouhii.module.menu.data.MenuItem;
import com.thiennbao.kouhii.module.menu.data.MenuItemCreateRequest;
import com.thiennbao.kouhii.module.menu.data.MenuItemResponse;
import com.thiennbao.kouhii.module.menu.data.MenuItemUpdateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuService {
    MenuItemRepository menuItemRepository;
    private final MenuItemMapper menuItemMapper;

    List<MenuItemResponse> getMenuItems() {
        return menuItemRepository.findAll().stream().map(menuItemMapper::toResponse).toList();
    }

    MenuItemResponse getMenuItem(String id) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() -> new AppException(AppError.MENU_ITEM_NOT_FOUND));
        return menuItemMapper.toResponse(menuItem);
    }

    @PreAuthorize("hasAuthority('CREATE_MENU_ITEM')")
    MenuItemResponse createMenuItem(MenuItemCreateRequest request) {
        MenuItem menuItem = menuItemMapper.toEntity(request);
        try {
            return menuItemMapper.toResponse(menuItemRepository.save(menuItem));
        } catch (DataIntegrityViolationException _) {
            throw new AppException(AppError.MENU_ITEM_NAME_CONFLICT);
        }
    }

    @PreAuthorize("hasAuthority('UPDATE_MENU_ITEM')")
    MenuItemResponse updateMenuItem(String id, MenuItemUpdateRequest request) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() -> new AppException(AppError.MENU_ITEM_NOT_FOUND));
        menuItemMapper.update(menuItem, request);
        try {
            return menuItemMapper.toResponse(menuItemRepository.save(menuItem));
        } catch (DataIntegrityViolationException _) {
            throw new AppException(AppError.MENU_ITEM_NAME_CONFLICT);
        }
    }

    @PreAuthorize("hasAuthority('DELETE_MENU_ITEM')")
    MenuItemResponse deleteMenuItem(String id) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() -> new AppException(AppError.MENU_ITEM_NOT_FOUND));
        menuItemRepository.delete(menuItem);
        return menuItemMapper.toResponse(menuItem);
    }
}
