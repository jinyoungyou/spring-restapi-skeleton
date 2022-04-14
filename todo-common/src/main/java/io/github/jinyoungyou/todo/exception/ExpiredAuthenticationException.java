package io.github.jinyoungyou.todo.exception;

import io.github.jinyoungyou.todo.exception.vo.ErrorCode;

public class ExpiredAuthenticationException extends CustomException {
    public ExpiredAuthenticationException() {
        super(ErrorCode.EXPIRED_AUTHENTICATION);
    }

}
