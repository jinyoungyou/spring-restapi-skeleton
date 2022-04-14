package io.github.jinyoungyou.todo.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> {
    private String resultCode;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pagination pagination;

    public static <T> Response<T> of(T payload) {
        //noinspection unchecked
        return (Response<T>) Response.builder()
                .resultCode("000")
                .message("success")
                .payload(payload)
                .build();
    }

    public static <T> Response<T> of(T payload, Pagination pagination) {
        //noinspection unchecked
        return (Response<T>) Response.builder()
                .resultCode("000")
                .message("success")
                .payload(payload)
                .pagination(pagination)
                .build();
    }
}
