package com.thiennbao.kouhii.module.account;

import com.thiennbao.kouhii.common.response.ApiResponse;
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
    ApiResponse<List<AccountResponse>> getAccounts() {
        return ApiResponse.success(accountService.getAccounts());
    }

    @GetMapping("/{id}")
    ApiResponse<AccountResponse> getAccount(@PathVariable("id") String id) {
        return ApiResponse.success(accountService.getAccount(id));
    }

    @PostMapping
    ApiResponse<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        return ApiResponse.success(accountService.createAccount(request));
    }

    @PutMapping("/{id}")
    ApiResponse<AccountResponse> updateAccount(@PathVariable("id") String id, @RequestBody AccountRequest request) {
        return ApiResponse.success(accountService.updateAccount(id, request));
    }

    @DeleteMapping("/{id}")
    ApiResponse<AccountResponse> deleteAccount(@PathVariable("id") String id) {
        return ApiResponse.success(accountService.deleteAccount(id));
    }
}
