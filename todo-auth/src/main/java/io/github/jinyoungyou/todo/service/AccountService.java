package io.github.jinyoungyou.todo.service;

import io.github.jinyoungyou.todo.controller.dto.AccountDataMapper;
import io.github.jinyoungyou.todo.controller.dto.AccountRequest;
import io.github.jinyoungyou.todo.controller.dto.AccountResponse;
import io.github.jinyoungyou.todo.controller.dto.Response;
import io.github.jinyoungyou.todo.account.domain.Account;
import io.github.jinyoungyou.todo.exception.EmailAlreadyExistsException;
import io.github.jinyoungyou.todo.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public Response<AccountResponse> create(AccountRequest accountRequest) {
        accountRepository.findByEmail(accountRequest.getEmail())
                .ifPresent(account -> {
                    throw new EmailAlreadyExistsException();
                });

        Account account = AccountDataMapper.fromRequest(accountRequest);

        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));

        Account newAccount = accountRepository.save(account);

        System.out.println("" + newAccount);

        return Response.of(AccountDataMapper.toResponse(newAccount));
    }
}
