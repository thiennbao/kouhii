package com.thiennbao.kouhii.module.account;

import com.thiennbao.kouhii.module.account.data.Account;
import com.thiennbao.kouhii.module.account.data.AccountRequest;
import com.thiennbao.kouhii.module.account.data.AccountResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("NOT_FOUND"));
        return accountMapper.toResponse(account);
    }

    AccountResponse createAccount(AccountRequest request) {
        Account account = accountMapper.toEntity(request);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    AccountResponse updateAccount(String id, AccountRequest request) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("NOT_FOUND"));
        accountMapper.update(account, request);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    AccountResponse deleteAccount(String id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("NOT_FOUND"));
        accountRepository.delete(account);
        return accountMapper.toResponse(account);
    }
}
