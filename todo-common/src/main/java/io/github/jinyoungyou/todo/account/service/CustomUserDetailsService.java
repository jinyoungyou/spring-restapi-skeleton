package io.github.jinyoungyou.todo.account.service;

import io.github.jinyoungyou.todo.account.domain.Account;
import io.github.jinyoungyou.todo.exception.AuthenticationFailException;
import io.github.jinyoungyou.todo.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(AuthenticationFailException::new);
    }

    private UserDetails createUserDetails(Account account) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(account.getAuthority().toString());

        return new User(String.valueOf(account.getId()),
                account.getPassword(),
                Collections.singleton(grantedAuthority));
    }
}
