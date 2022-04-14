package io.github.jinyoungyou.todo.service;

import io.github.jinyoungyou.todo.controller.dto.*;
import io.github.jinyoungyou.todo.exception.AuthenticationFailException;
import io.github.jinyoungyou.todo.exception.EmailAlreadyExistsException;
import io.github.jinyoungyou.todo.exception.ExpiredAuthenticationException;
import io.github.jinyoungyou.todo.exception.InvalidJwtTokenException;
import io.github.jinyoungyou.todo.jwt.JwtManager;
import io.github.jinyoungyou.todo.jwt.dto.JwtDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    // 생성자 주입

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtManager jwtManager;

    public Response<JwtDto> getToken(AccountRequest accountRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                accountRequest.toUsernamePasswordAuthenticationToken();

        Authentication authentication = authenticate(usernamePasswordAuthenticationToken)
                .orElseThrow(AuthenticationFailException::new);

        JwtDto jwt = jwtManager.createJwt(authentication);

        // Todo : Redis 같은 토큰 저장소에 리프레쉬 토큰 저장 필요

        return Response.of(jwt);
    }

    public Optional<Authentication> authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws EmailAlreadyExistsException {
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);

        return Optional.ofNullable(authentication);
    }

    public Response<JwtDto> refreshToken(TokenRequest tokenRequest) {
        // JWT 관련 Exception Handling 필요 e.g.) io.jsonwebtoken.ExpiredJwtException 등
        try {
            Claims refreshTokenClaims = jwtManager.getClaims(tokenRequest.getRefreshToken());
        }
        catch (ExpiredJwtException e) {
            throw new ExpiredAuthenticationException();
        }
        catch (JwtException e) {
            throw new InvalidJwtTokenException();
        }
        // Todo : Redis 같은 토큰 저장소에서 어카운트 확인, 만료 여부 등 확인 필요

        Authentication authentication = jwtManager.getAuthentication(tokenRequest.getRefreshToken());
        JwtDto jwt = jwtManager.createJwt(authentication);

        // Todo : Redis 같은 토큰 저장소에 리프레쉬 토큰 저장 필요

        return Response.of(jwt);
    }
}
