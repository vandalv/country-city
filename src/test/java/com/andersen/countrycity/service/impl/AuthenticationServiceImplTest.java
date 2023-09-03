package com.andersen.countrycity.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.andersen.countrycity.dto.AuthenticationRequestDTO;
import com.andersen.countrycity.dto.AuthenticationResponseDTO;
import com.andersen.countrycity.entity.User;
import com.andersen.countrycity.exception.UserNotFoundException;
import com.andersen.countrycity.repository.UserRepository;
import com.andersen.countrycity.data.TestDataFactory;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    private User user;
    private String email;
    private String password;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtServiceImpl jwtService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        user = TestDataFactory.user1();
        email = TestDataFactory.email();
        password = TestDataFactory.password();
    }

    @Test
    void authenticate_success() {

        AuthenticationRequestDTO request = new AuthenticationRequestDTO(email, password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("jwtToken");

        AuthenticationResponseDTO response = authenticationService.authenticate(request);

        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByEmail(email);
        verify(jwtService).generateToken(user);

        verifyNoMoreInteractions(authenticationManager, userRepository, jwtService);
    }

    @Test
    void authenticate_userNotFound() {

        AuthenticationRequestDTO request = new AuthenticationRequestDTO(email, password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> authenticationService.authenticate(request));

        verify(userRepository).findByEmail(email);
    }
}