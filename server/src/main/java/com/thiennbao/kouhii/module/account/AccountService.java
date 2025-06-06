package com.thiennbao.kouhii.module.account;

import com.thiennbao.kouhii.common.exception.AppError;
import com.thiennbao.kouhii.common.exception.AppException;
import com.thiennbao.kouhii.module.account.data.Account;
import com.thiennbao.kouhii.module.account.data.AccountCreateRequest;
import com.thiennbao.kouhii.module.account.data.AccountResponse;
import com.thiennbao.kouhii.module.account.data.AccountUpdateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    PasswordEncoder passwordEncoder;
    AccountRoleService accountRoleService;

    @PreAuthorize("hasAuthority('READ_ACCOUNT')")
    List<AccountResponse> getAccounts() {
        return accountRepository.findAll().stream().map(accountMapper::toResponse).toList();
    }

    @PreAuthorize("hasAuthority('READ_ACCOUNT')")
    AccountResponse getAccount(String id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(AppError.ACCOUNT_NOT_FOUND));
        return accountMapper.toResponse(account);
    }

    AccountResponse createAccount(AccountCreateRequest request) {
        Account account = accountMapper.toEntity(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        request.getRoles().forEach(role -> accountRoleService.addRole(account, role));
        try {
            return accountMapper.toResponse(accountRepository.save(account));
        } catch (DataIntegrityViolationException exception) {
            if (exception.getCause() instanceof ConstraintViolationException) {
                throw new AppException(AppError.USERNAME_CONFLICT);
            }
            throw exception;
        }
    }

    @PreAuthorize("hasAuthority('UPDATE_ACCOUNT_INFO')")
    AccountResponse updateAccount(String id, AccountUpdateRequest request) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(AppError.ACCOUNT_NOT_FOUND));
        accountMapper.update(account, request);
        if (request.getPassword() != null) {
            account.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getRoles() != null) {
            account.setRoles(new HashSet<>());
            request.getRoles().forEach(role -> accountRoleService.addRole(account, role));
        }
        try {
            return accountMapper.toResponse(accountRepository.save(account));
        } catch (DataIntegrityViolationException _) {
            throw new AppException(AppError.USERNAME_CONFLICT);
        }
    }

    @PreAuthorize("hasAuthority('DELETE_ACCOUNT')")
    AccountResponse deleteAccount(String id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(AppError.ACCOUNT_NOT_FOUND));
        accountRepository.delete(account);
        return accountMapper.toResponse(account);
    }

    AccountResponse addRole(String id, String role) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(AppError.ACCOUNT_NOT_FOUND));
        accountRoleService.addRole(account, role);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    AccountResponse removeRole(String id, String role) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(AppError.ACCOUNT_NOT_FOUND));
        accountRoleService.removeRole(account, role);
        return accountMapper.toResponse(accountRepository.save(account));
    }
}
