package com.example.CyrsachJava.controller;

import com.example.CyrsachJava.dto.RegistrationDTO;
import com.example.CyrsachJava.model.Role;
import com.example.CyrsachJava.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;


    @PostMapping("/student")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        registrationDTO.setRole(Role.ROLE_USER);
        return ResponseEntity.ok(registrationService.registerUser(registrationDTO));
    }
}
