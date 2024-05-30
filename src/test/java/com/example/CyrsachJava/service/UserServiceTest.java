package com.example.CyrsachJava.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.CyrsachJava.dto.UserDTO;
import com.example.CyrsachJava.mapper.UserMapper;
import com.example.CyrsachJava.model.Role;
import com.example.CyrsachJava.model.User;
import com.example.CyrsachJava.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole(Role.ROLE_USER);

        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("testuser");
        userDTO.setPassword("password");
        userDTO.setRole(Role.ROLE_USER);
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        when(userMapper.toDto(user)).thenReturn(userDTO);

        List<UserDTO> result = userService.getAllUsers();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUsername()).isEqualTo("testuser");
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).toDto(user);
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO result = userService.getUserById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testuser");
        verify(userRepository, times(1)).findById(1L);
        verify(userMapper, times(1)).toDto(user);
    }

    @Test
    void testCreateUser() {
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO result = userService.createUser(userDTO);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testuser");
        verify(userMapper, times(1)).toEntity(userDTO);
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toDto(user);
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO result = userService.updateUser(1L, userDTO);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testuser");
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toDto(user);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testIsAdmin() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        boolean result = userService.isAdmin(1L);

        assertThat(result).isFalse();
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetByUsername() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        User result = userService.getByUsername("testuser");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testuser");
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void testGetByUsernameThrowsException() {
        when(userRepository.findByUsername("invaliduser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.getByUsername("invaliduser"));

        verify(userRepository, times(1)).findByUsername("invaliduser");
    }
}
