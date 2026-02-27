package kkkvd.operator.operatorkvd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//AuthController — API для работы с аутентификацией
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    //Возвращает информацию о текущем авторизованном пользователе
    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> getCurrentUser(Authentication authentication) {
        // Получаем username из объекта Authentication
        String username = authentication.getName();
        // Получаем роль. Одна роль на пользователя — берём первую
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(a -> a.replace("ROLE_", ""))
                .findFirst()
                .orElse("UNKNOWN");
        return ResponseEntity.ok(Map.of("username", username, "role", role));
    }
}
