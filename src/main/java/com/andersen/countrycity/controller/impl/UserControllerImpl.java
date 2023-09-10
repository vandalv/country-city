package com.andersen.countrycity.controller.impl;

import com.andersen.countrycity.controller.UserController;
import com.andersen.countrycity.dto.AuthenticationRequestDTO;
import com.andersen.countrycity.dto.AuthenticationResponseDTO;
import com.andersen.countrycity.dto.RegistrationRequestDTO;
import com.andersen.countrycity.service.impl.AuthenticationServiceImpl;
import com.andersen.countrycity.service.impl.UserServiceImpl;
import com.andersen.countrycity.validation.ValidationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final AuthenticationServiceImpl authenticationServiceImpl;
    private final ValidationService validationService;
    private final UserServiceImpl userService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @Valid
            @RequestBody AuthenticationRequestDTO authRequest,
            BindingResult bindingResult) {

        validationService.handleValidation(bindingResult);

        return ResponseEntity.ok(authenticationServiceImpl.authenticate(authRequest));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/register")
    public void registerUser(
            @Valid
            @RequestBody RegistrationRequestDTO registrationRequest,
            BindingResult bindingResult) {

        validationService.handleValidation(bindingResult);
        userService.createUser(registrationRequest.getEmail(), registrationRequest.getPassword());
    }
}
