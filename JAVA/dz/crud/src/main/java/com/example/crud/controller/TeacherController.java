package com.example.crud.controller;

import com.example.crud.model.ErrorResponse;
import com.example.crud.model.Teacher;
import com.example.crud.model.TeacherDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
            validationMessages.add("Subject is required");
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

    @GetMapping("/active")
    public ResponseEntity<?> getActiveTeachers() {
        if (teachers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<Teacher> foundTeachers = teachers.stream().filter(Teacher::isActive).toList();
        if (foundTeachers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(foundTeachers);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getTeacherCount() {
        return ResponseEntity.ok(Map.of("count", teachers.size()));
    }

    @GetMapping("/count-by-subject")
    public ResponseEntity<?> getTeacherCountBySubject() {
        Map<String, Integer> groupSub = new HashMap<>();
        if (teachers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        for (Teacher teacher : teachers) {
            String subject = teacher.getSubject().trim();
            if (groupSub.containsKey(subject)) {
                groupSub.put(subject, groupSub.get(subject) + 1);
            } else {
                groupSub.put(subject, 1);
            }
            if (groupSub.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(groupSub);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeacher(@RequestBody TeacherDTO teacher) {
        List<String> validationMessages = new ArrayList<>();
        if (teacher.getFirstName() == null || teacher.getFirstName().isBlank()) {
            validationMessages.add("First name is required");
        }
        if (teacher.getFirstName().length() < 2 || teacher.getFirstName().length() > 50){
            validationMessages.add("First name must be between 2 and 50 characters");
        }
        if (teacher.getLastName() == null || teacher.getLastName().isBlank()) {
            validationMessages.add("Last name is required");
        }
        if (teacher.getLastName().length() < 2 || teacher.getLastName().length() > 50){
            validationMessages.add("Last name must be between 2 and 50 characters");
        }
        if (teacher.getSubject() == null || teacher.getSubject().isBlank()) {
            validationMessages.add("Subject is required");
        }
        if (teacher.getExperience() == null || teacher.getExperience() < 0 || teacher.getExperience() > 50) {
            validationMessages.add("Experience values must be between 0 and 50");
        }
        if (teacher.getSalary() == null || teacher.getSalary() < 0 || teacher.getSalary() > 100000) {
            validationMessages.add("Salary values must be between 0 and 100000");
        }
        if (teacher.getEmail() == null || !teacher.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
            validationMessages.add("Invalid email address");
        }

        if (!validationMessages.isEmpty()) {
            ErrorResponse response = ErrorResponse.badRequest("Validation failed", validationMessages);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        boolean duplicate = teachers.stream()
                .anyMatch(t -> t.getFirstName().equalsIgnoreCase(teacher.getFirstName()) &&
                t.getLastName().equalsIgnoreCase(teacher.getLastName()));
        if (duplicate) {
            validationMessages.add("Duplicate first name and last name");
            ErrorResponse response = ErrorResponse.conflict("Duplicate first name and last name");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        Teacher newTeacher = new Teacher(
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getSubject(),
                teacher.getExperience(),
                teacher.getSalary(),
                teacher.getEmail(),
                teacher.isActive()
        );
        newTeacher.setId(nextId++);
        teachers.add(newTeacher);

        return ResponseEntity.status(HttpStatus.CREATED).body(newTeacher);

    }
}
