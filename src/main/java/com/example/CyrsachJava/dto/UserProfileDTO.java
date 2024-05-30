package com.example.CyrsachJava.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long userId;
}
