package com.thiennbao.kouhii.module.menu.data;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    Double price;

    @Column(nullable = false)
    String image;
}
