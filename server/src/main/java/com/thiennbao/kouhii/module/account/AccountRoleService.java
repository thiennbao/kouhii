package com.thiennbao.kouhii.module.account;

import com.thiennbao.kouhii.module.account.data.Account;
import com.thiennbao.kouhii.module.role.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountRoleService {
    RoleService roleService;

    @PreAuthorize("hasAuthority('UPDATE_ACCOUNT_ROLE')")
    public void addRole(Account account, String role) {
        account.getRoles().add(roleService.getRoleByName(role));
    }

    @PreAuthorize("hasAuthority('UPDATE_ACCOUNT_ROLE')")
    public void removeRole(Account account, String role) {
        account.getRoles().remove(roleService.getRoleByName(role));
    }
}
