package com.thiennbao.kouhii.module.menu.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemCreateRequest {
    @NotBlank(message = "MENU_ITEM_NAME_INVALID")
    String name;

    @NotBlank(message = "MENU_ITEM_DESCRIPTION_INVALID")
    String description;

    @NotNull(message = "MENU_ITEM_PRICE_INVALID")
    @Min(value = 0, message = "MENU_ITEM_PRICE_INVALID")
    Double price;

    @NotBlank(message = "MENU_IMAGE_INVALID")
    String image;
}
