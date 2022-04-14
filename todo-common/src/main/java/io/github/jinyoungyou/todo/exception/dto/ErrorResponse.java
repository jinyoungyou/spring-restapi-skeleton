package io.github.jinyoungyou.todo.exception.dto;

import io.github.jinyoungyou.todo.exception.vo.ErrorCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String code;
    private String message;

    public static ErrorResponse of(ErrorCode e) {
        return ErrorResponse.builder()
                .status(e.getStatus())
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }
}
