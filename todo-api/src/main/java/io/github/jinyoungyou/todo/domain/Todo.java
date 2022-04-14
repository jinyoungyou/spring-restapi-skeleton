package io.github.jinyoungyou.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    private Integer status;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private Long createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private Long updatedBy;

    public void update(Todo update) {
        this.title = update.title;
        this.description = update.description;
        this.status = update.status;
    }

    public void patch(Todo patch) {
        if (patch.title != null)
            this.title = patch.title;
        if (patch.description != null)
            this.description = patch.description;
        if (patch.status != null)
            this.status = patch.status;
    }
}
