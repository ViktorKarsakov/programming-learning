package com.example.taskproject.factories;

import com.example.taskproject.dto.TaskStateDto;
import com.example.taskproject.entities.TaskStateEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskStateDtoFactory {
    public TaskStateDto createTaskStateDto(TaskStateEntity taskStateEntity) {
        return new TaskStateDto(
                taskStateEntity.getId(),
                taskStateEntity.getName(),
                taskStateEntity.getOrdinal(),
                taskStateEntity.getCreateAt()
        );
    }
}
