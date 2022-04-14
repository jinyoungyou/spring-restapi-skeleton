package io.github.jinyoungyou.todo.controller.dto;

import io.github.jinyoungyou.todo.domain.Todo;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class TodoRequest {
    @NotBlank
    @Size(min=2, max=30)
    private String title;

    @Size(max=300)
    private String description;

    private Integer status;
}
