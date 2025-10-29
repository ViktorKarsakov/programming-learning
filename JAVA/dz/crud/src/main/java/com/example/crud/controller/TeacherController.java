package com.example.crud.controller;

import com.example.crud.model.ErrorResponse;
import com.example.crud.model.Teacher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    List<Teacher> teachers;
    private Integer nextId = 1;

    public TeacherController() {

        teachers = new ArrayList<>();
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
        List<String> validationMessages = new ArrayList<>();

        if (id == null || id <= 0) {
            validationMessages.add("Id must be a positive integer");
            ErrorResponse response = ErrorResponse.badRequest("Validation failed", validationMessages);
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Teacher> foundTeacher = teachers.stream().filter(t -> t.getId().equals(id)).findFirst();

        if (foundTeacher.isEmpty()){
            ErrorResponse response = ErrorResponse.notFound("Teacher with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(foundTeacher.get());

    }

    @GetMapping("/search")
    public ResponseEntity<?> getTeacherByName(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName) {

        List<String> validationMessages = new ArrayList<>();

        if (firstName != null && !firstName.isBlank() && !firstName.matches("^[A-Za-zА-Яа-яЁё]+$")){
            validationMessages.add("First name must contain only letters");
        }
        if (lastName != null && !lastName.isBlank() && !lastName.matches("^[A-Za-zА-Яа-яЁё]+$")){
            validationMessages.add("Last name must contain only letters");
        }
        if (firstName != null && !firstName.isBlank() && (firstName.length() < 2 || firstName.length() > 50)){
            validationMessages.add("First name must be between 2 and 50 characters");
        }
        if (lastName != null && !lastName.isBlank() && (lastName.length() < 2 || lastName.length() > 50)){
            validationMessages.add("Last name must be between 2 and 50 characters");
        }

        if (!validationMessages.isEmpty()) {
            ErrorResponse response = ErrorResponse.badRequest("Validation failed", validationMessages);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if ((firstName == null || firstName.isBlank()) && (lastName == null || lastName.isBlank())){
            if (teachers.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(teachers);
        }

        String fn = firstName.toLowerCase().trim();
        String ln = lastName.toLowerCase().trim();

        List<Teacher> foundTeachers = teachers.stream().filter(t -> t.getFirstName().toLowerCase().trim().contains(fn) || t.getLastName().toLowerCase().trim().contains(ln)).toList();
        if (foundTeachers.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(foundTeachers);
    }

    @GetMapping("/{subject}")
    public ResponseEntity<?> getTeacherBySubject(@PathVariable(name = "subject") String subject) {
        List<String> validationMessages = new ArrayList<>();
        if (subject == null || subject.trim().isEmpty()){
            validationMessages.add("Subject must not be empty");
        }
        if (!validationMessages.isEmpty()) {
            ErrorResponse response = ErrorResponse.badRequest("Validation failed", validationMessages);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        String sub = subject.toLowerCase().trim();

        List<Teacher> foundTeachers = teachers.stream().filter(t -> t.getSubject().toLowerCase().trim().contains(sub)).toList();
        if (foundTeachers.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(foundTeachers);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getTeacherByExpAndSalary(
            @RequestParam(name = "minExp", required = false) Integer minExp,
            @RequestParam(name = "maxExp", required = false) Integer maxExp,
            @RequestParam(name = "minSalary", required = false) Integer minSalary,
            @RequestParam(name = "maxSalary", required = false) Integer maxSalary) {

        List<String> validationMessages = new ArrayList<>();
        if (minExp > maxExp) {
            validationMessages.add("Min exp must be greater than max exp");
        }
        if (minSalary > maxSalary) {
            validationMessages.add("Min salary must be greater than max salary");
        }
        if (minExp < 0 || maxExp < 0){
            validationMessages.add("Experience values cannot be negative");
        }
        if (minSalary < 0 || maxSalary < 0){
            validationMessages.add("Salary values cannot be negative");
        }
        if (!validationMessages.isEmpty()) {
            ErrorResponse response = ErrorResponse.badRequest("Validation failed", validationMessages);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (minExp == null && maxExp == null && minSalary == null && maxSalary == null) {
            return ResponseEntity.noContent().build();
        }

        List<Teacher> foundTeachers = teachers.stream()
                .filter(t ->
                (minExp == null || t.getExperience() >= minExp) &&
                (maxExp == null || t.getExperience() <= maxExp) &&
                (minSalary == null || t.getSalary() >= minSalary) &&
                (maxSalary == null || t.getSalary() <= maxSalary))
                .toList();
        if (foundTeachers.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(foundTeachers);
    }

    
}
