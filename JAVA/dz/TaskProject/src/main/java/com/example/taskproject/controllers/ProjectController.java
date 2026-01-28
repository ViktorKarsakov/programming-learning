package com.example.taskproject.controllers;

import com.example.taskproject.dto.ProjectDto;
import com.example.taskproject.factories.ProjectDtoFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task/project")
public class ProjectController {
   private ProjectDtoFactory projectDtoFactory;

   public ProjectController(ProjectDtoFactory projectDtoFactory) {
        this.projectDtoFactory = projectDtoFactory;
   }

   @PostMapping("/add")
    public ProjectDto addProject(@RequestBody ProjectDto projectDto) {

   }

}
