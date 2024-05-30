package com.example.CyrsachJava.dto;

import com.example.CyrsachJava.model.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;
}
