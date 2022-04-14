package io.github.jinyoungyou.todo.controller;

import io.github.jinyoungyou.todo.domain.Todo;
import io.github.jinyoungyou.todo.service.TodoService;
import io.github.jinyoungyou.todo.controller.dto.Response;
import io.github.jinyoungyou.todo.controller.dto.TodoRequest;
import io.github.jinyoungyou.todo.controller.dto.TodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    // 생성자 주입
    private final TodoService todoService;

    @PostMapping("")
    public Response<TodoResponse> create(@Valid @RequestBody TodoRequest request) {
        return todoService.create(request);
    }

    @GetMapping("/{id}")
    public Response<TodoResponse> read(@PathVariable Long id) {
        return todoService.read(id);
    }

//    @PutMapping("/{id}")
//    public Response<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) {
//        return todoService.update(request);
//    }

    @PutMapping("/{id}")
    public Response<TodoResponse> update(@PathVariable Long id, @Valid @RequestBody TodoRequest request) {
        return todoService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public Response<TodoResponse> delete(@PathVariable Long id) {
        return todoService.delete(id);
    }

    @GetMapping("")
    public Response<List<TodoResponse>> search(@PageableDefault(size=20) Pageable pagable) {
        return todoService.search(pagable);
    }

}
