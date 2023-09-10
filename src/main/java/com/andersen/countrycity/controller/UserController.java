package com.andersen.countrycity.controller;

import com.andersen.countrycity.dto.AuthenticationRequestDTO;
import com.andersen.countrycity.dto.AuthenticationResponseDTO;
import com.andersen.countrycity.dto.RegistrationRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Users Controller", description = "Controller For Managing User Information")
public interface UserController {

    @PostMapping("/authenticate")
    @Operation(
            summary = "Authenticate User",
            description = "Authenticates a user and returns an authentication response."
    )
    ResponseEntity<AuthenticationResponseDTO> authenticate(
            @Valid
            @RequestBody AuthenticationRequestDTO authRequest,
            BindingResult bindingResult
    );

    @PostMapping("/register")
    @Operation(
            summary = "Register User",
            description = "Registers a new user."
    )
    void registerUser(
            @Valid
            @RequestBody RegistrationRequestDTO registrationRequest,
            BindingResult bindingResult
    );
}
