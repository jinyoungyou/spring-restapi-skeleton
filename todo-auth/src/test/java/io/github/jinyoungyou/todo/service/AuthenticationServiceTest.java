package io.github.jinyoungyou.todo.service;

import io.github.jinyoungyou.todo.account.domain.Account;
import io.github.jinyoungyou.todo.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AuthenticationServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void createTest() {
    }
}