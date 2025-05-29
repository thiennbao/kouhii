package com.thiennbao.kouhii.module.account;

import com.thiennbao.kouhii.module.account.data.AccountRequest;
import com.thiennbao.kouhii.module.account.data.AccountResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService accountService;

    @GetMapping
    List<AccountResponse> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    AccountResponse getAccount(@PathVariable("id") String id) {
        return accountService.getAccount(id);
    }

    @PostMapping
    AccountResponse createAccount(@RequestBody AccountRequest request) {
        return accountService.createAccount(request);
    }

    @PutMapping("/{id}")
    AccountResponse updateAccount(@PathVariable("id") String id, @RequestBody AccountRequest request) {
        return accountService.updateAccount(id, request);
    }

    @DeleteMapping("/{id}")
    AccountResponse deleteAccount(@PathVariable("id") String id) {
        return accountService.deleteAccount(id);
    }
}
