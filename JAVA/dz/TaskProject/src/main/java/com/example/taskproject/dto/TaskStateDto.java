package com.example.taskproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.NonNull;

import java.time.LocalDateTime;

public class TaskStateDto {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Long ordinal;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public TaskStateDto(@NonNull Long id, @NonNull String name, @NonNull Long ordinal, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.ordinal = ordinal;
        this.createdAt = createdAt;


    }

    public @NonNull Long getId() {
        return id;
    }

    public @NonNull String getName() {
        return name;
    }

    public @NonNull Long getOrdinal() {
        return ordinal;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
