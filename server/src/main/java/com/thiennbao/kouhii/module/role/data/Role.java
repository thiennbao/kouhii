package com.thiennbao.kouhii.module.role.data;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, unique = true)
    String name;

    @Column
    String description;

    @ElementCollection(targetClass = Permission.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<Permission> permissions;
}
