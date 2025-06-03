package com.thiennbao.kouhii.module.account;

import com.thiennbao.kouhii.module.account.data.Account;
import com.thiennbao.kouhii.module.account.data.AccountRequest;
import com.thiennbao.kouhii.module.account.data.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "id", ignore = true)
    Account toEntity(AccountRequest accountRequest);

    AccountResponse toResponse(Account account);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Account account, AccountRequest request);
}
