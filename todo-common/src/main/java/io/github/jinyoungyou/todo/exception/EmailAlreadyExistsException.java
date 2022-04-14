package io.github.jinyoungyou.todo.exception;

import io.github.jinyoungyou.todo.exception.vo.ErrorCode;

public class EmailAlreadyExistsException extends CustomException {
    public EmailAlreadyExistsException() {
        super(ErrorCode.EMAIL_ALREADY_EXISTS);
    }

}
