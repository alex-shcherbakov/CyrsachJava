package com.example.CyrsachJava.service;

import com.example.CyrsachJava.dto.UserProfileDTO;
import com.example.CyrsachJava.mapper.UserProfileMapper;
import com.example.CyrsachJava.model.UserProfile;
import com.example.CyrsachJava.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    public UserProfileService(UserProfileRepository userProfileRepository, UserProfileMapper userProfileMapper) {
        this.userProfileRepository = userProfileRepository;
        this.userProfileMapper = userProfileMapper;
    }

    public List<UserProfileDTO> getAllUserProfiles() {
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        return userProfiles.stream()
                .map(userProfileMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserProfileDTO getUserProfileById(Long id) {
        return userProfileRepository.findById(id)
                .map(userProfileMapper::toDto)
                .orElse(null);
    }

    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) {
        UserProfile userProfile = userProfileMapper.toEntity(userProfileDTO);
        UserProfile savedUserProfile = userProfileRepository.save(userProfile);
        return userProfileMapper.toDto(savedUserProfile);
    }

    public UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO) {
        UserProfile existingUserProfile = userProfileRepository.findById(id)
                .orElse(null);
        if (existingUserProfile != null) {
            existingUserProfile.setFirstName(userProfileDTO.getFirstName());
            existingUserProfile.setLastName(userProfileDTO.getLastName());
            existingUserProfile.setEmail(userProfileDTO.getEmail());
            UserProfile updatedUserProfile = userProfileRepository.save(existingUserProfile);
            return userProfileMapper.toDto(updatedUserProfile);
        }
        return null;
    }

    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }
}



