package com.example.CyrsachJava.service;

import com.example.CyrsachJava.dto.RegistrationDTO;
import com.example.CyrsachJava.dto.auth.SignInRequest;
import com.example.CyrsachJava.mapper.RegistrationMapper;
import com.example.CyrsachJava.model.Role;
import com.example.CyrsachJava.model.User;
import com.example.CyrsachJava.model.UserProfile;
import com.example.CyrsachJava.repository.UserProfileRepository;
import com.example.CyrsachJava.repository.UserRepository;
import com.example.CyrsachJava.service.auth.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final RegistrationMapper registrationMapper;

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String registerUser(RegistrationDTO registrationDTO) {
        User user = registrationMapper.toUserEntity(registrationDTO);
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        userRepository.save(user);

        if (registrationDTO.getRole().equals(Role.ROLE_USER)) {
            UserProfile studentProfile = registrationMapper.toUserProfileEntity(registrationDTO);
            studentProfile.setUser(user);
            userProfileRepository.save(studentProfile);
        }
        return jwtService.generateToken((UserDetails) user);
    }

    public String signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserDetails userDetails = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        return jwtService.generateToken(userDetails);
    }
}
