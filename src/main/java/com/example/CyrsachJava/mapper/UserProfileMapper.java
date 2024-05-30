package com.example.CyrsachJava.mapper;

import com.example.CyrsachJava.dto.UserProfileDTO;
import com.example.CyrsachJava.model.UserProfile;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {
    private final ModelMapper modelMapper;

    public UserProfileMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserProfileDTO toDto(UserProfile userProfile) {
        return modelMapper.map(userProfile, UserProfileDTO.class);
    }

    public UserProfile toEntity(UserProfileDTO userProfileDTO) {
        return modelMapper.map(userProfileDTO, UserProfile.class);
    }
}

