package com.mamun.apiplatform.dto;

import com.mamun.apiplatform.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
}