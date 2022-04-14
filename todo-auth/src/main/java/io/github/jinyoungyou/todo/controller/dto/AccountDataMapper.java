package io.github.jinyoungyou.todo.controller.dto;

import io.github.jinyoungyou.todo.account.domain.Account;
import io.github.jinyoungyou.todo.jwt.vo.Authority;

public class AccountDataMapper {
    public static Account fromRequest(AccountRequest accountRequest) {
        return Account.builder()
                .email(accountRequest.getEmail())
                .password(accountRequest.getPassword())
                .authority(Authority.ROLE_USER)
                .build();
    }

    public static AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .email(account.getEmail())
                .authority(account.getAuthority())
                .build();
    }
}
