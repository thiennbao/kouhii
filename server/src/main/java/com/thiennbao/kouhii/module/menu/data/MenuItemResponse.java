package com.thiennbao.kouhii.module.menu.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemResponse {
    String id;
    String name;
    String description;
    Double price;
    String image;
}
