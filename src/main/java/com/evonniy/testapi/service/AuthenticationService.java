package com.evonniy.testapi.service;

import com.evonniy.testapi.exception.exceptions.UserAlreadyExistException;
import com.evonniy.testapi.exception.exceptions.YouAreNotAuthorizedException;
import com.evonniy.testapi.model.dto.MessageDto;
import com.evonniy.testapi.model.dto.RegisterRequestDto;
import com.evonniy.testapi.model.entity.User;
import com.evonniy.testapi.model.enums.Role;
import com.evonniy.testapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public MessageDto register(RegisterRequestDto request) {
        if (repository.findByUsername(request.getName()).isPresent()){
            throw new UserAlreadyExistException();
        }

        var user = User.builder()
                .username(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);
        return new MessageDto("Registered");
    }

    public User getAndCheckRealUser() {
        Optional<User> isUser = getUserFromAuth();
        if (isUser.isEmpty()) {
            throw new YouAreNotAuthorizedException();
        }
        return isUser.get();
    }

    public Optional<User> getUserFromAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return repository.findByUsername(currentPrincipalName);
    }
}

