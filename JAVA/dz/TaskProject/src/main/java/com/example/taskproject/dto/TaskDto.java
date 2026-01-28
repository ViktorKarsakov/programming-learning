package com.example.taskproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;

public class TaskDto {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @NonNull
    private String description;

    public TaskDto(@NonNull Long id, @NonNull String name, LocalDateTime createdAt, @NonNull String description) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.description = description;
    }

    public @NonNull Long getId() {
        return id;
    }

    public @NonNull String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public @NonNull String getDescription() {
        return description;
    }
}
