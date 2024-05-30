package com.example.CyrsachJava.dto;

import com.example.CyrsachJava.model.Role;
import lombok.Data;

@Data
public class RegistrationDTO {
    private String username;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private String email;
}
