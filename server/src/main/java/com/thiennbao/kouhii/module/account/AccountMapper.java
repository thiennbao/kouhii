package com.thiennbao.kouhii.module.account;

import com.thiennbao.kouhii.module.account.data.Account;
import com.thiennbao.kouhii.module.account.data.AccountCreateRequest;
import com.thiennbao.kouhii.module.account.data.AccountResponse;
import com.thiennbao.kouhii.module.account.data.AccountUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    Account toEntity(AccountCreateRequest request);

    AccountResponse toResponse(Account account);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void update(@MappingTarget Account account, AccountUpdateRequest request);
}
