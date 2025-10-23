package com.example.dz_crud_operation.controller;


import com.example.dz_crud_operation.model.Students;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentsController {
    List<Students> students;

    public StudentsController() {
        students = new ArrayList<>();
    }

    @GetMapping("/all")
    public List<Students> getAll() {
        return students;
    }

    @GetMapping("/by-id/{id}")
    public Students byID(@PathVariable(name = "id") int id) {
        for (Students student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @GetMapping("/search")
    public List<Students> search(
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "surname", required = false) String surname
    ){
        List<Students> result = students.stream().filter(student ->
            (name == null || student.getName().toLowerCase().contains(name.toLowerCase().trim())) &&
            (surname == null || student.getSurname().toLowerCase().contains(surname.toLowerCase().trim()))
        ).toList();
        return result;
    }
}



