package com.thiennbao.kouhii.module.menu;

import com.thiennbao.kouhii.module.menu.data.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, String> {
}
