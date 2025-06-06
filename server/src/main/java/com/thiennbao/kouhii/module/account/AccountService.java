package com.thiennbao.kouhii.module.account;

import com.thiennbao.kouhii.common.exception.AppError;
import com.thiennbao.kouhii.common.exception.AppException;
import com.thiennbao.kouhii.module.account.data.Account;
import com.thiennbao.kouhii.module.account.data.AccountCreateRequest;
import com.thiennbao.kouhii.module.account.data.AccountResponse;
import com.thiennbao.kouhii.module.account.data.AccountUpdateRequest;
import com.thiennbao.kouhii.module.role.RoleService;
import com.thiennbao.kouhii.module.role.data.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    PasswordEncoder passwordEncoder;
    RoleService roleService;

    List<AccountResponse> getAccounts() {
        return accountRepository.findAll().stream().map(accountMapper::toResponse).toList();
    }

    AccountResponse getAccount(String id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(AppError.ACCOUNT_NOT_FOUND));
        return accountMapper.toResponse(account);
    }

    AccountResponse createAccount(AccountCreateRequest request) {
        Account account = accountMapper.toEntity(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Role> roles = request.getRoles().stream().map(roleService::getRoleByName).collect(Collectors.toSet());
        account.setRoles(roles);
        try {
            return accountMapper.toResponse(accountRepository.save(account));
        } catch (DataIntegrityViolationException exception) {
            if (exception.getCause() instanceof ConstraintViolationException) {
                throw new AppException(AppError.USERNAME_CONFLICT);
            }
            throw exception;
        }
    }

    AccountResponse updateAccount(String id, AccountUpdateRequest request) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(AppError.ACCOUNT_NOT_FOUND));
        accountMapper.update(account, request);
        if (request.getPassword() != null) {
            account.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getRoles() != null) {
            Set<Role> roles = request.getRoles().stream().map(roleService::getRoleByName).collect(Collectors.toSet());
            account.setRoles(roles);
        }
        try {
            return accountMapper.toResponse(accountRepository.save(account));
        } catch (DataIntegrityViolationException _) {
            throw new AppException(AppError.USERNAME_CONFLICT);
        }
    }

    AccountResponse deleteAccount(String id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(AppError.ACCOUNT_NOT_FOUND));
        accountRepository.delete(account);
        return accountMapper.toResponse(account);
    }

    AccountResponse addRole(String id, String role) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(AppError.ACCOUNT_NOT_FOUND));
        account.getRoles().add(roleService.getRoleByName(role));
        return accountMapper.toResponse(accountRepository.save(account));
    }

    AccountResponse removeRole(String id, String role) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(AppError.ACCOUNT_NOT_FOUND));
        account.getRoles().remove(roleService.getRoleByName(role));
        return accountMapper.toResponse(accountRepository.save(account));
    }
}
