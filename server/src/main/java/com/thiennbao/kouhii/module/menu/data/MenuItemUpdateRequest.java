package com.thiennbao.kouhii.module.menu.data;

import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemUpdateRequest {
    String name;

    String description;

    @Min(value = 0, message = "MENU_ITEM_PRICE_INVALID")
    Double price;

    String image;
}
