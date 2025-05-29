package com.thiennbao.kouhii.module.account;

import com.thiennbao.kouhii.module.account.data.Account;
import com.thiennbao.kouhii.module.account.data.AccountRequest;
import com.thiennbao.kouhii.module.account.data.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountRequest accountRequest);

    AccountResponse toResponse(Account account);

    void update(@MappingTarget Account account, AccountRequest request);
}
