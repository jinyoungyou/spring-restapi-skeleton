package io.github.jinyoungyou.todo.exception;

import io.github.jinyoungyou.todo.exception.vo.ErrorCode;
import lombok.Getter;

public class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException() {
        super(ErrorCode.RESOURCE_NOT_FOUND);
    }
}
