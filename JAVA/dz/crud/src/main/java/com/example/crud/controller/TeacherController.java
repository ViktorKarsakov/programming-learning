package com.example.crud.controller;

import com.example.crud.model.ErrorResponse;
import com.example.crud.model.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    List<Teacher> teachers = new ArrayList<>();
    private int nextId = 1;

    public TeacherController() {

      Teacher teacher1 = new Teacher("Ольга", "Петрова", "Math", 5, 95000.0, "op@mail.ru", true);
      teacher1.setId(nextId++);
      teachers.add(teacher1);

      Teacher teacher2 = new Teacher("Дмитрий", "Иванов", "Physics", 12, 93000.5, "di@mail.ru", true);
      teacher2.setId(nextId++);
      teachers.add(teacher2);

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTeachers() {
        if (teachers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable(name = "id") Integer id) {
        if (id == null || id <= 0) {
            ErrorResponse response = ErrorResponse.
            return
        }
    }

}
