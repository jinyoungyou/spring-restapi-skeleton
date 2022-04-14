package io.github.jinyoungyou.todo.exception;

import io.github.jinyoungyou.todo.exception.vo.ErrorCode;

public class InvalidJwtTokenException extends CustomException {
    public InvalidJwtTokenException() {
        super(ErrorCode.INVALID_JWT_TOKEN);
    }
}
