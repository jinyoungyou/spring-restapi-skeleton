package io.github.jinyoungyou.todo.controller.dto;

import lombok.Data;

@Data
public class TokenRequest {
    private String accessToken;
    private String refreshToken;
}
