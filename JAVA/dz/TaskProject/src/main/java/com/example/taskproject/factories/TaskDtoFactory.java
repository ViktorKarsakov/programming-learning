package com.example.taskproject.factories;

import com.example.taskproject.dto.TaskDto;
import com.example.taskproject.entities.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoFactory {
    public TaskDto createTaskDto(TaskEntity taskEntity) {
        return new TaskDto(
                taskEntity.getId(),
                taskEntity.getName(),
                taskEntity.getCreateAt(),
                taskEntity.getDescription()
        );
    }
}
