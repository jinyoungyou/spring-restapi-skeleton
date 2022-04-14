package io.github.jinyoungyou.todo.exception.vo;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_JWT_TOKEN(400, "400", "Invalid JWT token / 비정상적인 JWT 토큰입니다."),
    INVALID_INPUT_VALUE(401, "401", "Authentication failed / 인증에 실패했습니다."),
    AUTHENTICATION_FAIL(403, "403", "Wrong username or password / 아이디나 비밀번호가 틀렸습니다."),
    RESOURCE_NOT_FOUND(404, "404", "Not found / 검색 결과가 없습니다."),
    EMAIL_ALREADY_EXISTS(409, "409", "Email already exists / 이미 사용된 이메일입니다."),
    EXPIRED_AUTHENTICATION(440, "440", "Expired authentication / 인증이 만료되었습니다."),
    ;

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
