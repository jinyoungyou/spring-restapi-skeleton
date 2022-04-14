package io.github.jinyoungyou.todo.controller.dto;

import io.github.jinyoungyou.todo.jwt.vo.Authority;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Builder
public class AccountResponse {
    private Long id;

    private String email;

    private String nickname;

    private Authority authority;
}
