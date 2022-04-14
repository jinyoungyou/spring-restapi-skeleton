package io.github.jinyoungyou.todo.controller;

import io.github.jinyoungyou.todo.controller.dto.*;
import io.github.jinyoungyou.todo.jwt.dto.JwtDto;
import io.github.jinyoungyou.todo.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/token")
    public Response<JwtDto> getToken(@RequestBody AccountRequest accountRequest) {
        return authenticationService.getToken(accountRequest);
    }

    @PutMapping("/token")
    public Response<JwtDto> refreshToken(@RequestBody TokenRequest tokenRequest) {
        return authenticationService.refreshToken(tokenRequest);
    }
}
