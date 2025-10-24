package com.example.dz_crud_operation.controller;


import com.example.dz_crud_operation.model.Students;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentsController {
    List<Students> students;
    int nextId = 1;

    public StudentsController() {
        students = new ArrayList<>();
    }

    @GetMapping("/add")
    public String add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "age") int age,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "email") String email
    ){
        name = name.trim();
        surname = surname.trim();
        email = email.trim().toLowerCase();
        phone = (phone == null) ? null : phone.trim();

        if(name.isEmpty() || surname.isEmpty() || email.isEmpty()){
            return "Ошибка: имя, фамилия или email не могут быть пустыми";
        }
        int at = email.indexOf('@');
        int dot =  email.lastIndexOf('.');
        if(at <= 0 || dot <= at + 1 || dot >= email.length()){
            return "Неправильный формат email";
        }

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

    @GetMapping("/by-email")
    public Students findByEmail(@RequestParam (name = "email") String email){
        for (Students student : students) {
            if (student.getEmail().trim().toLowerCase().equals(email.trim().toLowerCase())) {
                return student;
            }
        }
        return null;
    }

    @GetMapping("/filter-by-age")
    public List<Students> filterByAge (@RequestParam(name = "min") int min, @RequestParam(name = "max") int max){
        List<Students> result = students.stream().filter(student ->
               student.getAge() >= min && student.getAge() <= max).toList();
        return result;
    }


}



