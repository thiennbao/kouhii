package com.thiennbao.kouhii.module.account;

import com.thiennbao.kouhii.common.exception.AppError;
import com.thiennbao.kouhii.common.exception.AppException;
import com.thiennbao.kouhii.module.account.data.Account;
import com.thiennbao.kouhii.module.account.data.AccountRequest;
import com.thiennbao.kouhii.module.account.data.AccountResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;

    List<AccountResponse> getAccounts() {
        return accountRepository.findAll().stream().map(accountMapper::toResponse).toList();
    }

    AccountResponse getAccount(String id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(AppError.ACCOUNT_NOT_FOUND));
        return accountMapper.toResponse(account);
    }

    AccountResponse createAccount(AccountRequest request) {
        Account account = accountMapper.toEntity(request);
        try {
            return accountMapper.toResponse(accountRepository.save(account));
        } catch (DataIntegrityViolationException _) {
            throw new AppException(AppError.USERNAME_CONFLICT);
        }
    }

    AccountResponse updateAccount(String id, AccountRequest request) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AppException(AppError.ACCOUNT_NOT_FOUND));
        accountMapper.update(account, request);
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
}
