package io.github.jinyoungyou.todo.controller;

import io.github.jinyoungyou.todo.controller.dto.AccountRequest;
import io.github.jinyoungyou.todo.controller.dto.AccountResponse;
import io.github.jinyoungyou.todo.controller.dto.Response;
import io.github.jinyoungyou.todo.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("")
    public Response<AccountResponse> register(@RequestBody AccountRequest accountRequest) {
        return accountService.create(accountRequest);
    }
}
