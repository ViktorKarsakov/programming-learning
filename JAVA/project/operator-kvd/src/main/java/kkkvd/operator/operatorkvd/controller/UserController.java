package kkkvd.operator.operatorkvd.controller;

import kkkvd.operator.operatorkvd.dto.CreateUserRequest;
import kkkvd.operator.operatorkvd.dto.UserResponse;
import kkkvd.operator.operatorkvd.entities.User;
import kkkvd.operator.operatorkvd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//UserController — API для управления пользователями системы
//Доступ только для пользователей с ролью ADMIN
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //Получить список всех пользователей
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> result = userService.getAllUsers().stream().map(UserResponse::fromEntity).toList();
        return ResponseEntity.ok(result);
    }

    //Создать нового пользователя
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        User created = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.fromEntity(created));
    }

    //Заблокировать или разблокировать пользователя
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<UserResponse> toggleUser(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        boolean enabled = body.getOrDefault("enabled", true);
        User updated = userService.toggleEnabled(id, enabled);
        return ResponseEntity.ok(UserResponse.fromEntity(updated));
    }

    //Сбросить пароль пользователя
    @PatchMapping("/{id}/reset-password")
    public ResponseEntity<UserResponse> resetPassword(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String newPassword = body.get("newPassword");
        User updated = userService.resetPassword(id, newPassword);
        return ResponseEntity.ok(UserResponse.fromEntity(updated));
    }
}
