package com.example.taskproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private LocalDateTime createAt = LocalDateTime.now();

    @OneToMany(mappedBy = "project")
    private List<TaskStateEntity> tasksState = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public List<TaskStateEntity> getTasksState() {
        return tasksState;
    }

    public void setTasksState(List<TaskStateEntity> tasksState) {
        this.tasksState = tasksState;
    }
}
