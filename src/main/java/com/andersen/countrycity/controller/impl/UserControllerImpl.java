package com.andersen.countrycity.controller.impl;

import com.andersen.countrycity.controller.UserController;
import com.andersen.countrycity.dto.AuthenticationRequestDTO;
import com.andersen.countrycity.dto.AuthenticationResponseDTO;
import com.andersen.countrycity.dto.RegistrationRequestDTO;
import com.andersen.countrycity.service.impl.AuthenticationServiceImpl;
import com.andersen.countrycity.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final AuthenticationServiceImpl authenticationServiceImpl;
    private final UserServiceImpl userService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody AuthenticationRequestDTO authRequest) {

        return ResponseEntity.ok(authenticationServiceImpl.authenticate(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(
            @RequestBody RegistrationRequestDTO registrationRequest) {

        userService.createUser(registrationRequest.getEmail(), registrationRequest.getPassword());

        return ResponseEntity.ok().build();
    }
}
