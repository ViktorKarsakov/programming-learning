package kkkvd.operator.operatorkvd.dto;

import kkkvd.operator.operatorkvd.entities.Role;
import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
    private String fullName;
    private Role role;
}
