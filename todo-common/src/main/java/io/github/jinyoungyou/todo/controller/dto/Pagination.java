package io.github.jinyoungyou.todo.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {
    private Integer page;
    private Integer totalPages;
    private Integer elements;
    private Long totalElements;
}
