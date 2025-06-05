package com.thiennbao.kouhii.module.role;

import com.thiennbao.kouhii.module.role.data.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
