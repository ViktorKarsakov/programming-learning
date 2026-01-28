package com.example.taskproject.repositories;

import com.example.taskproject.entities.TaskStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStateRepository extends JpaRepository<TaskStateEntity,Long> {
}
