package com.thiennbao.kouhii.module.menu;

import com.thiennbao.kouhii.module.menu.data.MenuItem;
import com.thiennbao.kouhii.module.menu.data.MenuItemCreateRequest;
import com.thiennbao.kouhii.module.menu.data.MenuItemResponse;
import com.thiennbao.kouhii.module.menu.data.MenuItemUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MenuItemMapper {
    @Mapping(target = "id", ignore = true)
    MenuItem toEntity(MenuItemCreateRequest request);

    MenuItemResponse toResponse(MenuItem menuItem);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget MenuItem menuItem, MenuItemUpdateRequest request);
}
