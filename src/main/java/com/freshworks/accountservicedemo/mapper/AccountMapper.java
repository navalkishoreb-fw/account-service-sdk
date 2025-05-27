package com.freshworks.accountservicedemo.mapper;

import com.freshworks.accountservicedemo.dto.AccountResponse;
import com.freshworks.commons.v2.Account;

public class AccountMapper {

    public static AccountResponse toAccountResponse(Account account){
        return AccountResponse.builder()
                .id(account.getId().getValue())
                .organisationId(account.getOrganisationId().getValue())
                .productId(account.getProductId().getValue())
                .name(account.getName().getValue())
                .domain(account.getDomain().getValue())
                .status(account.getStatus().name())
                .build();
    }
}
