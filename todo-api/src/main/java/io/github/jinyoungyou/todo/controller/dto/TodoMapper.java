package io.github.jinyoungyou.todo.controller.dto;

import io.github.jinyoungyou.todo.domain.Todo;

public class TodoMapper {
    public static Todo fromRequest(TodoRequest request) {
        return Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .build();
    }
    public static TodoResponse toResponse(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .status(todo.getStatus())
                .createdAt(todo.getCreatedAt())
                .createdBy(todo.getCreatedBy())
                .updatedAt(todo.getUpdatedAt())
                .updatedBy(todo.getUpdatedBy())
                .build();
    }
}
