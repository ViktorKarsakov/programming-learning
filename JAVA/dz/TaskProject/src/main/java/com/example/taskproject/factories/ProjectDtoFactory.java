package com.example.taskproject.factories;

import com.example.taskproject.dto.ProjectDto;
import com.example.taskproject.entities.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectDtoFactory {
    public ProjectDto createProjectDto(ProjectEntity projectEntity) {
        return new ProjectDto(
                projectEntity.getId(),
                projectEntity.getName(),
                projectEntity.getCreateAt()
        );
    }
}
