package com.andersen.countrycity.service.impl;

import com.andersen.countrycity.entity.Role;
import com.andersen.countrycity.entity.User;
import com.andersen.countrycity.repository.UserRepository;
import com.andersen.countrycity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(String email, String password) {

        String encodedPassword = passwordEncoder.encode(password);

        User newUser = User.builder()
                .email(email)
                .password(encodedPassword)
                .role(Role.USER)
                .build();

        userRepository.save(newUser);
    }
}
