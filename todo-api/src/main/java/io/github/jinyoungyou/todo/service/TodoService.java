package io.github.jinyoungyou.todo.service;

import io.github.jinyoungyou.todo.controller.dto.*;
import io.github.jinyoungyou.todo.domain.Todo;
import io.github.jinyoungyou.todo.exception.ResourceNotFoundException;
import io.github.jinyoungyou.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    // 생성자 주입
    private final TodoRepository todoRepository;

    public Response<TodoResponse> read(Long id) {
        return todoRepository.findById(id)
                .map(todo -> Response.of(TodoMapper.toResponse(todo)))
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Response<TodoResponse> create(TodoRequest request) {
        Todo todo = TodoMapper.fromRequest(request);

        Todo newTodo = todoRepository.save(todo);

        return Response.of(TodoMapper.toResponse(newTodo));
    }

    public Response<TodoResponse> delete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        todoRepository.delete(todo);

        return Response.of(null);
    }

    public Response<TodoResponse> update(Long id, TodoRequest request) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        todo.patch(TodoMapper.fromRequest(request));

        Todo newTodo = todoRepository.save(todo);

        return Response.of(TodoMapper.toResponse(todo));
    }

    public Response<List<TodoResponse>> search(Pageable pageable) {
        Page<Todo> page = todoRepository.findAll(pageable);
        List<TodoResponse> todoList = page.stream()
                .map(TodoMapper::toResponse)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .page(page.getNumber())
                .totalPages(page.getTotalPages())
                .elements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .build();

        return Response.of(todoList, pagination);
    }
}
