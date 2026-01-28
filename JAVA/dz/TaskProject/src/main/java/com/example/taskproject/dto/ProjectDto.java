package com.example.taskproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;

public class ProjectDto {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public ProjectDto(@NonNull Long id, @NonNull String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
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
}
