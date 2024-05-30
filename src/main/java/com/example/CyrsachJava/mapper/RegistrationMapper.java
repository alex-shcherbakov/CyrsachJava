package com.example.CyrsachJava.mapper;

import com.example.CyrsachJava.dto.RegistrationDTO;
import com.example.CyrsachJava.model.User;
import com.example.CyrsachJava.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationMapper {
    private final ModelMapper modelMapper;

    public User toUserEntity(RegistrationDTO registrationDTO) {
        return modelMapper.map(registrationDTO, User.class);
    }

    public UserProfile toUserProfileEntity(RegistrationDTO registrationDTO) {
        return modelMapper.map(registrationDTO, UserProfile.class);
    }
}
