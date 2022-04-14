package io.github.jinyoungyou.todo.exception;

import io.github.jinyoungyou.todo.exception.vo.ErrorCode;

public class AuthenticationFailException extends CustomException {
    public AuthenticationFailException() {
        super(ErrorCode.AUTHENTICATION_FAIL);
    }

}
